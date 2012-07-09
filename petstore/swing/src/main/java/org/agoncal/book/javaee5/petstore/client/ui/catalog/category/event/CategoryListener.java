package org.agoncal.book.javaee5.petstore.client.ui.catalog.category.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;


public interface CategoryListener extends XSListener<CategoryEventPropertyName> {

    // ------------------------------------------------------------------------
    // 
    public void identifierChanged(XSEvent<CategoryEventPropertyName, Long> evt);

    public void nameChanged(XSEvent<CategoryEventPropertyName, String> evt);

    public void descriptionChanged(
            XSEvent<CategoryEventPropertyName, String> evt);

}