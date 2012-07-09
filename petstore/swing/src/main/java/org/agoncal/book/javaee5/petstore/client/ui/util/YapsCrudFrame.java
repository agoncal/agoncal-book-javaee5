package org.agoncal.book.javaee5.petstore.client.ui.util;


import org.agoncal.book.javaee5.petstore.client.ui.util.event.*;

import javax.swing.*;
import java.awt.*;
import java.util.EventObject;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsUIConstants.DEFAULT_BG_COLOR;


public abstract class YapsCrudFrame<P extends YapsComponentPane> extends
        YapsInternalFrame implements YapsViewerListener,
        YapsCrudActionListener, YapsFindActionListener,
        YapsCommonActionListener, YapsActionModel {

    protected final P mainPane;
    protected final YapsActionPane actionPane;

    protected JLabel labelTitle;


    public YapsCrudFrame(final P mainPane) {
        this.mainPane = mainPane;
        actionPane = new YapsActionPane(mainPane.getViewType());

        initComponent();

        mainPane.addYapsViewListener(this);

        actionPane.addYapsCrudActionListener(this);
        actionPane.addYapsFindActionListener(this);
        actionPane.addYapsCommonActionListener(this);
    }

    private void initComponent() {
        JPanel globalPane = new JPanel();
        globalPane.setLayout(new BorderLayout());

        JPanel mainBodyPane = new JPanel();
        mainBodyPane.setLayout(new BorderLayout());
        mainBodyPane.setBorder(BorderFactory.createLineBorder(Color.white, 10));

        JPanel mainContentPane = new JPanel();
        mainContentPane.setLayout(new BorderLayout());

        labelTitle = new JLabel();
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitle.setFont(new Font("Dialog", 1, 18));
        labelTitle.setBackground(Color.white);
        labelTitle.setOpaque(true);

        initTitle(mainPane.getViewType());

        mainContentPane.add(labelTitle, BorderLayout.NORTH);

        JPanel mainBorderedPane = new JPanel();
        mainBorderedPane.setBackground(DEFAULT_BG_COLOR);
        mainBorderedPane.setBorder(BorderFactory.createEtchedBorder());
        mainBorderedPane.setLayout(new BorderLayout());
        mainBorderedPane.add(mainPane, BorderLayout.CENTER);

        mainContentPane.add(mainBorderedPane, BorderLayout.CENTER);

        mainBodyPane.add(mainContentPane, BorderLayout.CENTER);
        globalPane.add(mainBodyPane, BorderLayout.CENTER);

        JPanel actionBodyPane = new JPanel();
        actionBodyPane.setLayout(new BorderLayout());
        actionBodyPane.setBorder(BorderFactory
                .createLineBorder(Color.white, 10));
        actionBodyPane.add(actionPane, BorderLayout.SOUTH);

        globalPane.add(actionBodyPane, BorderLayout.SOUTH);

        setInnerPane(globalPane);
    }


    public void yapsViewChanged(YapsViewerEvent evt) {
        actionPane.setViewType(evt.getNewValue());

        initTitle(evt.getNewValue());
    }


    public void addYapsCrudActionListener(YapsCrudActionListener listener) {
        actionPane.addYapsCrudActionListener(listener);
    }

    public void addYapsFindActionListener(YapsFindActionListener listener) {
        actionPane.addYapsFindActionListener(listener);
    }

    public void addYapsCommonActionListener(YapsCommonActionListener listener) {
        actionPane.addYapsCommonActionListener(listener);
    }


    public void removeYapsCrudActionListener(YapsCrudActionListener listener) {
        actionPane.removeYapsCrudActionListener(listener);
    }

    public void removeYapsFindActionListener(YapsFindActionListener listener) {
        actionPane.removeYapsFindActionListener(listener);
    }

    public void removeYapsCommonActionListener(YapsCommonActionListener listener) {
        actionPane.removeYapsCommonActionListener(listener);
    }


    public YapsCrudActionListener[] getYapsCrudActionListeners() {
        return actionPane.getYapsCrudActionListeners();
    }

    public YapsFindActionListener[] getYapsFindActionListeners() {
        return actionPane.getYapsFindActionListeners();
    }

    public YapsCommonActionListener[] getYapsCommonActionListeners() {
        return actionPane.getYapsCommonActionListeners();
    }


    public void closeActionPerformed(EventObject evt) {
        dispose();
    }


    protected void initTitle(YapsViewType viewType) {
        labelTitle.setText(mainPane.toString() + " (" + viewType.getLabel()
                + ")");

        setTitle(mainPane.toString() + " (" + viewType.getLabel() + ")");
    }

}