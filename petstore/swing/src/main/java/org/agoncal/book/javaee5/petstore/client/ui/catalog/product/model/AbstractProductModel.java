package org.agoncal.book.javaee5.petstore.client.ui.catalog.product.model;


import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.event.ProductEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.event.ProductListener;
import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;

import static org.agoncal.book.javaee5.petstore.client.ui.catalog.product.event.ProductEventPropertyName.*;


public abstract class AbstractProductModel extends
        AbstractXSModel<ProductListener, ProductEventPropertyName> implements
        ProductModel {

    @Override
    @SuppressWarnings("unchecked")
    protected void listenerMethodCaller(ProductListener listener,
                                        XSEvent<ProductEventPropertyName, ?> evt) {
        ProductEventPropertyName propertyName = evt.getPropertyNameEnumType();

        if (propertyName == IDENTIFIER_CHANGED) {
            listener
                    .identifierChanged((XSEvent<ProductEventPropertyName, Long>) evt);
        } else if (propertyName == NAME_CHANGED) {
            listener
                    .nameChanged((XSEvent<ProductEventPropertyName, String>) evt);
        } else if (propertyName == DESCRIPTION_CHANGED) {
            listener
                    .descriptionChanged((XSEvent<ProductEventPropertyName, String>) evt);
        } else if (propertyName == CATEGORY_CHANGED) {
            listener
                    .categoryChanged((XSEvent<ProductEventPropertyName, Category>) evt);
        }
    }

}