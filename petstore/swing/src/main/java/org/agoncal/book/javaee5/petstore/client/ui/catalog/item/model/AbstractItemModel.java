package org.agoncal.book.javaee5.petstore.client.ui.catalog.item.model;


import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.event.ItemEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.event.ItemListener;
import org.agoncal.book.javaee5.petstore.entity.catalog.Product;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;

import static org.agoncal.book.javaee5.petstore.client.ui.catalog.item.event.ItemEventPropertyName.*;


public abstract class AbstractItemModel extends
        AbstractXSModel<ItemListener, ItemEventPropertyName> implements
        ItemModel {

    @Override
    @SuppressWarnings("unchecked")
    protected void listenerMethodCaller(ItemListener listener,
                                        XSEvent<ItemEventPropertyName, ?> evt) {
        ItemEventPropertyName propertyName = evt.getPropertyNameEnumType();

        if (propertyName == IDENTIFIER_CHANGED) {
            listener
                    .identifierChanged((XSEvent<ItemEventPropertyName, Long>) evt);
        } else if (propertyName == NAME_CHANGED) {
            listener.nameChanged((XSEvent<ItemEventPropertyName, String>) evt);
        } else if (propertyName == UNIT_COST_CHANGED) {
            listener
                    .unitCostChanged((XSEvent<ItemEventPropertyName, Float>) evt);
        } else if (propertyName == IMAGE_PATH_CHANGED) {
            listener
                    .imagePathChanged((XSEvent<ItemEventPropertyName, String>) evt);
        } else if (propertyName == PRODUCT_CHANGED) {
            listener
                    .productChanged((XSEvent<ItemEventPropertyName, Product>) evt);
        }
    }

}