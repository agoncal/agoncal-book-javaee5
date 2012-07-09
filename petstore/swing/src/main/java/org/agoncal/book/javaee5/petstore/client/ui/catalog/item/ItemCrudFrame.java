package org.agoncal.book.javaee5.petstore.client.ui.catalog.item;


import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.event.ItemAdapter;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.event.ItemEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.model.ItemModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;
import org.agoncal.book.javaee5.petstore.delegate.CatalogDelegate;
import org.agoncal.book.javaee5.petstore.entity.catalog.Item;
import org.agoncal.book.javaee5.petstore.entity.catalog.Product;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import javax.swing.*;
import java.util.EventObject;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.*;


public class ItemCrudFrame extends YapsCrudFrame<ItemPane> {

    private static final long serialVersionUID = -5830165357963216102L;


    public ItemCrudFrame(final ItemPane mainPane) {
        super(mainPane);

        mainPane.getModel().addXSListener(new ItemAdapter() {

            @Override
            public void identifierChanged(
                    XSEvent<ItemEventPropertyName, Long> evt) {
                initTitle(mainPane.getViewType());
            }

        });
    }


    public void findActionPerformed(EventObject evt) {
        final String actionName = "find";

        ItemModel model = mainPane.getModel();
        Long identifier = model.getIdentifierToFind();

        if (identifier == null) {
            displayWarning("Identifier must be non null");

            return;
        }

        try {
            Item item = CatalogDelegate.findItem(identifier);

            if (item == null) {
                JOptionPane.showMessageDialog(this,
                        "This item has not been found", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                model.setItem(item);

                mainPane.setViewType(UPDATE_OR_DELETE);
            }
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }


    public void createActionPerformed(EventObject evt) {
        final String actionName = "create";

        ItemModel model = mainPane.getModel();
        Item item = model.getItem();

        try {
            Product product = CatalogDelegate.findProduct(model.getProduct()
                    .getId());
            item = CatalogDelegate.createItem(item, product);

            dispose();
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }

    public void readActionPerformed(EventObject evt) {
    }

    public void updateActionPerformed(EventObject evt) {
        final String actionName = "update";

        ItemModel model = mainPane.getModel();
        Item item = model.getItem();

        try {
            Product product = CatalogDelegate.findProduct(model.getProduct()
                    .getId());
            item = CatalogDelegate.updateItem(item, product);

            dispose();
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }

    public void deleteActionPerformed(EventObject evt) {
        final String actionName = "delete";

        ItemModel model = mainPane.getModel();
        Item item = model.getItem();

        try {
            CatalogDelegate.deleteItem(item);

            dispose();
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }


    public void resetActionPerformed(EventObject evt) {
        ItemModel model = mainPane.getModel();
        YapsViewType viewType = mainPane.getViewType();

        if (viewType != FIND || viewType != CREATE
                || viewType != FIND_OR_CREATE) {
            model.setIdentifierToFind(null);
        }

        model.reset();
    }

}