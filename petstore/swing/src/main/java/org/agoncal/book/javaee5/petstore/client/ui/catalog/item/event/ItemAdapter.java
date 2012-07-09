package org.agoncal.book.javaee5.petstore.client.ui.catalog.item.event;


import org.agoncal.book.javaee5.petstore.entity.catalog.Product;
import org.vstm.fwk.client.ui.xswing.core.event.XSAdapter;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;


public class ItemAdapter extends XSAdapter<ItemEventPropertyName> implements
        ItemListener {

    public void identifierChanged(XSEvent<ItemEventPropertyName, Long> evt) {
    }

    public void nameChanged(XSEvent<ItemEventPropertyName, String> evt) {
    }

    public void unitCostChanged(XSEvent<ItemEventPropertyName, Float> evt) {
    }

    public void imagePathChanged(XSEvent<ItemEventPropertyName, String> evt) {
    }

    public void productChanged(XSEvent<ItemEventPropertyName, Product> evt) {
    }

}