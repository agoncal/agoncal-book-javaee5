package org.agoncal.book.javaee5.petstore.client.ui.util.event;


import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;

import java.util.EventObject;


public class YapsViewerEvent extends EventObject {

    private static final long serialVersionUID = 1064634318266602259L;


    private final YapsViewType oldValue;
    private final YapsViewType newValue;


    public YapsViewerEvent(Object source, YapsViewType oldValue,
                           YapsViewType newValue) {
        super(source);

        this.oldValue = oldValue;
        this.newValue = newValue;
    }


    public YapsViewType getOldValue() {
        return oldValue;
    }

    public YapsViewType getNewValue() {
        return newValue;
    }

}