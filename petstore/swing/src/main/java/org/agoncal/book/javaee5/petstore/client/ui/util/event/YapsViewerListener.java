package org.agoncal.book.javaee5.petstore.client.ui.util.event;


import java.util.EventListener;


public interface YapsViewerListener extends EventListener {

    public void yapsViewChanged(YapsViewerEvent evt);

}