package org.agoncal.book.javaee5.petstore.client.ui.catalog.category.model;


import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.event.CategoryEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.event.CategoryListener;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;

import static org.agoncal.book.javaee5.petstore.client.ui.catalog.category.event.CategoryEventPropertyName.*;


public abstract class AbstractCategoryModel extends
        AbstractXSModel<CategoryListener, CategoryEventPropertyName> implements
        CategoryModel {

    @Override
    @SuppressWarnings("unchecked")
    protected void listenerMethodCaller(CategoryListener listener,
                                        XSEvent<CategoryEventPropertyName, ?> evt) {
        CategoryEventPropertyName propertyName = evt.getPropertyNameEnumType();

        if (propertyName == IDENTIFIER_CHANGED) {
            listener
                    .identifierChanged((XSEvent<CategoryEventPropertyName, Long>) evt);
        } else if (propertyName == NAME_CHANGED) {
            listener
                    .nameChanged((XSEvent<CategoryEventPropertyName, String>) evt);
        } else if (propertyName == DESCRIPTION_CHANGED) {
            listener
                    .descriptionChanged((XSEvent<CategoryEventPropertyName, String>) evt);
        }
    }

}