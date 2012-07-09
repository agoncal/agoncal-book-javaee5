package org.agoncal.book.javaee5.petstore.client.ui.util;


import org.agoncal.book.javaee5.petstore.client.ui.PetstoreFrame;
import org.agoncal.book.javaee5.petstore.client.ui.util.event.YapsCommonActionListener;
import org.agoncal.book.javaee5.petstore.client.ui.util.event.YapsCrudActionAdapter;
import org.agoncal.book.javaee5.petstore.client.ui.util.event.YapsCrudActionListener;
import org.agoncal.book.javaee5.petstore.client.ui.util.event.YapsFindActionListener;

import javax.swing.*;
import java.awt.*;
import java.util.EventObject;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsUIConstants.DEFAULT_BG_COLOR;
import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.*;


public class YapsListFrame extends YapsInternalFrame implements
        YapsCrudActionListener, YapsFindActionListener,
        YapsCommonActionListener {

    private static final long serialVersionUID = 1680009092050042538L;


    protected YapsTable table;
    protected YapsActionPane actionPane;


    public YapsListFrame(YapsTableModel model) {
        final String actionName = "init";

        try {
            table = new YapsTable(model);
            actionPane = new YapsActionPane(LIST);

            initComponent();

            setSize(getDefaultSize());

            actionPane.addYapsCrudActionListener(this);
            actionPane.addYapsFindActionListener(this);
            actionPane.addYapsCommonActionListener(this);
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }


    protected Dimension getDefaultSize() {
        return new Dimension(550, 400);
    }


    private void initComponent() {
        setTitle(table.getYapsModel().getDefaultTitle());

        JPanel globalPane = new JPanel();
        globalPane.setLayout(new BorderLayout());

        JPanel mainBodyPane = new JPanel();
        mainBodyPane.setLayout(new BorderLayout());
        mainBodyPane.setBorder(BorderFactory.createLineBorder(Color.white, 10));

        JPanel mainBorderedPane = new JPanel();
        mainBorderedPane.setBackground(DEFAULT_BG_COLOR);
        mainBorderedPane.setBorder(BorderFactory.createEtchedBorder());
        mainBorderedPane.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(DEFAULT_BG_COLOR);
        mainBorderedPane.add(scrollPane, BorderLayout.CENTER);

        mainBodyPane.add(mainBorderedPane, BorderLayout.CENTER);
        globalPane.add(mainBodyPane, BorderLayout.CENTER);

        JPanel actionBodyPane = new JPanel();
        actionBodyPane.setLayout(new BorderLayout());
        actionBodyPane.setBorder(BorderFactory
                .createLineBorder(Color.white, 10));
        actionBodyPane.add(actionPane, BorderLayout.SOUTH);

        globalPane.add(actionBodyPane, BorderLayout.SOUTH);

        setInnerPane(globalPane);
    }


    public void findActionPerformed(EventObject evt) {
        PetstoreFrame.getInstance().addAndShowFrame(
                table.getYapsModel().crudFrameFactory(null, FIND));
    }


    public void createActionPerformed(EventObject evt) {
        YapsCrudFrame frame = table.getYapsModel().crudFrameFactory(null,
                CREATE);

        frame.addYapsCrudActionListener(new YapsCrudActionAdapter() {

            @Override
            public void createActionPerformed(EventObject evt) {
                table.getYapsModel().initDataList();
            }

        });

        PetstoreFrame.getInstance().addAndShowFrame(frame);
    }

    public void readActionPerformed(EventObject evt) {
        showFrame(READ);
    }

    public void updateActionPerformed(EventObject evt) {
        showFrame(UPDATE);
    }

    public void deleteActionPerformed(EventObject evt) {
        showFrame(DELETE);
    }


    public void resetActionPerformed(EventObject evt) {
    }

    public void closeActionPerformed(EventObject evt) {
        dispose();
    }


    protected void showFrame(YapsViewType viewType) {
        int[] selectedRows = table.getSelectedRows();

        for (int selectedRowIndex : selectedRows) {
            YapsCrudFrame frame = table.getYapsModel().crudFrameFactory(
                    selectedRowIndex, viewType);

            if (viewType == UPDATE) {
                frame.addYapsCrudActionListener(new YapsCrudActionAdapter() {

                    @Override
                    public void updateActionPerformed(EventObject evt) {
                        table.getYapsModel().initDataList();
                    }

                });
            }

            if (viewType == DELETE) {
                frame.addYapsCrudActionListener(new YapsCrudActionAdapter() {

                    @Override
                    public void deleteActionPerformed(EventObject evt) {
                        table.getYapsModel().initDataList();
                    }

                });
            }

            PetstoreFrame.getInstance().addAndShowFrame(frame);
        }
    }

}