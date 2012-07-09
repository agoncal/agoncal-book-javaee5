package org.agoncal.book.javaee5.petstore.client.ui.util;


import org.agoncal.book.javaee5.petstore.client.ui.util.event.YapsCommonActionListener;
import org.agoncal.book.javaee5.petstore.client.ui.util.event.YapsCrudActionListener;
import org.agoncal.book.javaee5.petstore.client.ui.util.event.YapsFindActionListener;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsActionPane.YapsActionPropertyName.*;
import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.*;


public class YapsActionPane extends JPanel implements YapsActionModel {

    private static final long serialVersionUID = -4950626416337689544L;


    protected static enum YapsActionPropertyName {

        FIND_ACTION, CREATE_ACTION, READ_ACTION, UPDATE_ACTION, DELETE_ACTION, RESET_ACTION, CLOSE_ACTION

    }


    private final EventListenerList listenerList = new EventListenerList();
    private YapsViewType viewType;

    private JButton findBt;
    private JButton createBt;
    private JButton readBt;
    private JButton updateBt;
    private JButton deleteBt;
    private JButton resetBt;
    private JButton closeBt;


    public YapsActionPane(YapsViewType viewType) {
        initComponent();

        this.viewType = viewType;
        initViewType();
    }

    public YapsActionPane() {
        this(READ);
    }


    private void initComponent() {
        findBt = new JButton("Find");
        createBt = new JButton("Create");
        readBt = new JButton("View");
        updateBt = new JButton("Update");
        deleteBt = new JButton("Delete");
        resetBt = new JButton("Reset");
        closeBt = new JButton("Close");


        // this.viewType = READ;
        // initViewType();


        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());
        setOpaque(false);

        JPanel leftPane = new JPanel();
        JPanel centerPane = new JPanel();
        JPanel rightPane = new JPanel();

        leftPane.setOpaque(false);
        leftPane.add(findBt);
        add(leftPane, BorderLayout.WEST);

        centerPane.setOpaque(false);
        centerPane.add(createBt);
        centerPane.add(readBt);
        centerPane.add(updateBt);
        centerPane.add(deleteBt);
        add(centerPane, BorderLayout.CENTER);

        rightPane.setOpaque(false);
        rightPane.add(resetBt);
        rightPane.add(closeBt);
        add(rightPane, BorderLayout.EAST);


        findBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fireActionPerformed(FIND_ACTION);
            }
        });

        createBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fireActionPerformed(CREATE_ACTION);
            }
        });

        readBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fireActionPerformed(READ_ACTION);
            }
        });

        updateBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fireActionPerformed(UPDATE_ACTION);
            }
        });

        deleteBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fireActionPerformed(DELETE_ACTION);
            }
        });

        resetBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fireActionPerformed(RESET_ACTION);
            }
        });

        closeBt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fireActionPerformed(CLOSE_ACTION);
            }
        });
    }


    public void addYapsCrudActionListener(YapsCrudActionListener listener) {
        listenerList.add(YapsCrudActionListener.class, listener);
    }

    public void addYapsFindActionListener(YapsFindActionListener listener) {
        listenerList.add(YapsFindActionListener.class, listener);
    }

    public void addYapsCommonActionListener(YapsCommonActionListener listener) {
        listenerList.add(YapsCommonActionListener.class, listener);
    }


    public void removeYapsCrudActionListener(YapsCrudActionListener listener) {
        listenerList.remove(YapsCrudActionListener.class, listener);
    }

    public void removeYapsFindActionListener(YapsFindActionListener listener) {
        listenerList.remove(YapsFindActionListener.class, listener);
    }

    public void removeYapsCommonActionListener(YapsCommonActionListener listener) {
        listenerList.remove(YapsCommonActionListener.class, listener);
    }


    public YapsCrudActionListener[] getYapsCrudActionListeners() {
        return listenerList.getListeners(YapsCrudActionListener.class);
    }

    public YapsFindActionListener[] getYapsFindActionListeners() {
        return listenerList.getListeners(YapsFindActionListener.class);
    }

    public YapsCommonActionListener[] getYapsCommonActionListeners() {
        return listenerList.getListeners(YapsCommonActionListener.class);
    }


    private void fireActionPerformed(YapsActionPropertyName actionPropertyName) {
        Object[] listeners = listenerList.getListenerList();
        EventObject evt = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (evt == null) {
                evt = new EventObject(this);
            }

            if (listeners[i] == YapsCrudActionListener.class) {
                if (actionPropertyName == CREATE_ACTION) {
                    ((YapsCrudActionListener) listeners[i + 1])
                            .createActionPerformed(evt);
                } else if (actionPropertyName == READ_ACTION) {
                    ((YapsCrudActionListener) listeners[i + 1])
                            .readActionPerformed(evt);
                } else if (actionPropertyName == UPDATE_ACTION) {
                    ((YapsCrudActionListener) listeners[i + 1])
                            .updateActionPerformed(evt);
                } else if (actionPropertyName == DELETE_ACTION) {
                    ((YapsCrudActionListener) listeners[i + 1])
                            .deleteActionPerformed(evt);
                }
            } else if (listeners[i] == YapsFindActionListener.class) {
                if (actionPropertyName == FIND_ACTION) {
                    ((YapsFindActionListener) listeners[i + 1])
                            .findActionPerformed(evt);
                }
            } else if (listeners[i] == YapsCommonActionListener.class) {
                if (actionPropertyName == RESET_ACTION) {
                    ((YapsCommonActionListener) listeners[i + 1])
                            .resetActionPerformed(evt);
                } else if (actionPropertyName == CLOSE_ACTION) {
                    ((YapsCommonActionListener) listeners[i + 1])
                            .closeActionPerformed(evt);
                }
            }
        }
    }


    public YapsViewType getViewType() {
        return viewType;
    }

    public void setViewType(YapsViewType viewType) {
        this.viewType = viewType;

        initViewType();
    }

    private void initViewType() {
        if (viewType == LIST) {
            resetBt.setVisible(false);

            return;
        }

        readBt.setVisible(false);
        closeBt.setEnabled(true);

        if (viewType == FIND) {
            findBt.setEnabled(true);
            createBt.setEnabled(false);
            readBt.setEnabled(true);
            updateBt.setEnabled(false);
            deleteBt.setEnabled(false);
            resetBt.setEnabled(true);
        } else if (viewType == CREATE) {
            findBt.setEnabled(false);
            createBt.setEnabled(true);
            readBt.setEnabled(true);
            updateBt.setEnabled(false);
            deleteBt.setEnabled(false);
            resetBt.setEnabled(true);
        } else if (viewType == FIND_OR_CREATE) {
            findBt.setEnabled(true);
            createBt.setEnabled(true);
            readBt.setEnabled(true);
            updateBt.setEnabled(false);
            deleteBt.setEnabled(false);
            resetBt.setEnabled(true);
        } else if (viewType == READ) {
            findBt.setEnabled(false);
            createBt.setEnabled(false);
            readBt.setEnabled(true);
            updateBt.setEnabled(false);
            deleteBt.setEnabled(false);
            resetBt.setEnabled(false);
        } else if (viewType == UPDATE) {
            findBt.setEnabled(false);
            createBt.setEnabled(false);
            readBt.setEnabled(true);
            updateBt.setEnabled(true);
            deleteBt.setEnabled(false);
            resetBt.setEnabled(true);
        } else if (viewType == DELETE) {
            findBt.setEnabled(false);
            createBt.setEnabled(false);
            readBt.setEnabled(true);
            updateBt.setEnabled(false);
            deleteBt.setEnabled(true);
            resetBt.setEnabled(false);
        } else if (viewType == UPDATE_OR_DELETE) {
            findBt.setEnabled(false);
            createBt.setEnabled(false);
            readBt.setEnabled(true);
            updateBt.setEnabled(true);
            deleteBt.setEnabled(true);
            resetBt.setEnabled(false);
        }
    }

}