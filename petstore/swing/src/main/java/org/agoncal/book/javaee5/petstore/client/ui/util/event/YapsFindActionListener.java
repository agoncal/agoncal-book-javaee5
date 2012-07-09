package org.agoncal.book.javaee5.petstore.client.ui.util.event;


import java.util.EventListener;
import java.util.EventObject;


public interface YapsFindActionListener extends EventListener {

    public void findActionPerformed(EventObject evt);

}