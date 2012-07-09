package org.agoncal.book.javaee5.petstore.client.ui.catalog.product;


import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.event.ProductAdapter;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.event.ProductEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.model.ProductModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;
import org.agoncal.book.javaee5.petstore.delegate.CatalogDelegate;
import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.agoncal.book.javaee5.petstore.entity.catalog.Product;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import javax.swing.*;
import java.util.EventObject;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.*;


public class ProductCrudFrame extends YapsCrudFrame<ProductPane> {

    private static final long serialVersionUID = -4459715124049741934L;


    public ProductCrudFrame(final ProductPane mainPane) {
        super(mainPane);

        mainPane.getModel().addXSListener(new ProductAdapter() {

            @Override
            public void identifierChanged(
                    XSEvent<ProductEventPropertyName, Long> evt) {
                initTitle(mainPane.getViewType());
            }

        });
    }


    public void findActionPerformed(EventObject evt) {
        final String actionName = "find";

        ProductModel model = mainPane.getModel();
        Long identifier = model.getIdentifierToFind();

        if (identifier == null) {
            displayWarning("Identifier must be non null");

            return;
        }

        try {
            Product product = CatalogDelegate.findProduct(identifier);

            if (product == null) {
                JOptionPane.showMessageDialog(this,
                        "This product has not been found", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                model.setProduct(product);

                mainPane.setViewType(UPDATE_OR_DELETE);
            }
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }


    public void createActionPerformed(EventObject evt) {
        final String actionName = "create";

        ProductModel model = mainPane.getModel();
        Product product = model.getProduct();

        try {
            Category category = CatalogDelegate.findCategory(model
                    .getCategory().getId());
            product = CatalogDelegate.createProduct(product, category);

            dispose();
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }

    public void readActionPerformed(EventObject evt) {
    }

    public void updateActionPerformed(EventObject evt) {
        final String actionName = "update";

        ProductModel model = mainPane.getModel();
        Product product = model.getProduct();

        try {
            Category category = CatalogDelegate.findCategory(model
                    .getCategory().getId());
            product = CatalogDelegate.updateProduct(product, category);

            dispose();
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }

    public void deleteActionPerformed(EventObject evt) {
        final String actionName = "delete";

        ProductModel model = mainPane.getModel();
        Product product = model.getProduct();

        try {
            CatalogDelegate.deleteProduct(product);

            dispose();
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }


    public void resetActionPerformed(EventObject evt) {
        ProductModel model = mainPane.getModel();
        YapsViewType viewType = mainPane.getViewType();

        if (viewType != FIND || viewType != CREATE
                || viewType != FIND_OR_CREATE) {
            model.setIdentifierToFind(null);
        }

        model.reset();
    }

}