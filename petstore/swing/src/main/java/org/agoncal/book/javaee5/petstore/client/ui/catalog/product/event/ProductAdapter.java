package org.agoncal.book.javaee5.petstore.client.ui.catalog.product.event;


import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.vstm.fwk.client.ui.xswing.core.event.XSAdapter;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;


public class ProductAdapter extends XSAdapter<ProductEventPropertyName>
        implements ProductListener {

    public void identifierChanged(XSEvent<ProductEventPropertyName, Long> evt) {
    }

    public void nameChanged(XSEvent<ProductEventPropertyName, String> evt) {
    }

    public void descriptionChanged(XSEvent<ProductEventPropertyName, String> evt) {
    }

    public void categoryChanged(XSEvent<ProductEventPropertyName, Category> evt) {
    }

}