package org.agoncal.book.javaee5.petstore.client.ui.catalog.category.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSAdapter;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;


public class CategoryAdapter extends XSAdapter<CategoryEventPropertyName>
        implements CategoryListener {

    public void identifierChanged(XSEvent<CategoryEventPropertyName, Long> evt) {
    }

    public void nameChanged(XSEvent<CategoryEventPropertyName, String> evt) {
    }

    public void descriptionChanged(
            XSEvent<CategoryEventPropertyName, String> evt) {
    }

}