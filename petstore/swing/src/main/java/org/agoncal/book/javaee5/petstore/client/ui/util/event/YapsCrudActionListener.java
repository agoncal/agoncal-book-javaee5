package org.agoncal.book.javaee5.petstore.client.ui.util.event;


import java.util.EventListener;
import java.util.EventObject;


public interface YapsCrudActionListener extends EventListener {

    public void createActionPerformed(EventObject evt);

    public void readActionPerformed(EventObject evt);

    public void updateActionPerformed(EventObject evt);

    public void deleteActionPerformed(EventObject evt);

}