package org.agoncal.book.javaee5.petstore.client.ui.util;


import org.agoncal.book.javaee5.petstore.client.ui.util.event.YapsCommonActionListener;
import org.agoncal.book.javaee5.petstore.client.ui.util.event.YapsCrudActionListener;
import org.agoncal.book.javaee5.petstore.client.ui.util.event.YapsFindActionListener;


public interface YapsActionModel {

    public void addYapsCrudActionListener(YapsCrudActionListener listener);

    public void addYapsFindActionListener(YapsFindActionListener listener);

    public void addYapsCommonActionListener(YapsCommonActionListener listener);


    public void removeYapsCrudActionListener(YapsCrudActionListener listener);

    public void removeYapsFindActionListener(YapsFindActionListener listener);

    public void removeYapsCommonActionListener(YapsCommonActionListener listener);


    public YapsCrudActionListener[] getYapsCrudActionListeners();

    public YapsFindActionListener[] getYapsFindActionListeners();

    public YapsCommonActionListener[] getYapsCommonActionListeners();

}