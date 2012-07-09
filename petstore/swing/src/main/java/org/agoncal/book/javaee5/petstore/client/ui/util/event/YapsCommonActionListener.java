package org.agoncal.book.javaee5.petstore.client.ui.util.event;


import java.util.EventListener;
import java.util.EventObject;


public interface YapsCommonActionListener extends EventListener {

    public void resetActionPerformed(EventObject evt);

    public void closeActionPerformed(EventObject evt);

}