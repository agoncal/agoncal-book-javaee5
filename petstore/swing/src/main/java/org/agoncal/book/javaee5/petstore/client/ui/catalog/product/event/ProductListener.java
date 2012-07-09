package org.agoncal.book.javaee5.petstore.client.ui.catalog.product.event;


import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;


public interface ProductListener extends XSListener<ProductEventPropertyName> {

    // ------------------------------------------------------------------------
    // 
    public void identifierChanged(XSEvent<ProductEventPropertyName, Long> evt);

    public void nameChanged(XSEvent<ProductEventPropertyName, String> evt);

    public void descriptionChanged(XSEvent<ProductEventPropertyName, String> evt);

    public void categoryChanged(XSEvent<ProductEventPropertyName, Category> evt);

}