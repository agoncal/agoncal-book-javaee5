package org.agoncal.book.javaee5.petstore.client.ui.catalog.category;


import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.event.CategoryAdapter;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.event.CategoryEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.model.CategoryModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;
import org.agoncal.book.javaee5.petstore.delegate.CatalogDelegate;
import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import javax.ejb.EJBException;
import javax.swing.*;
import java.util.EventObject;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.*;


public class CategoryCrudFrame extends YapsCrudFrame<CategoryPane> {

    private static final long serialVersionUID = 7769342743144999626L;


    public CategoryCrudFrame(final CategoryPane mainPane) {
        super(mainPane);

        mainPane.getModel().addXSListener(new CategoryAdapter() {

            @Override
            public void identifierChanged(
                    XSEvent<CategoryEventPropertyName, Long> evt) {
                initTitle(mainPane.getViewType());
            }

        });
    }


    public void findActionPerformed(EventObject evt) {
        final String actionName = "find";

        CategoryModel model = mainPane.getModel();
        Long identifier = model.getIdentifierToFind();

        if (identifier == null) {
            displayWarning("Identifier must be non null");

            return;
        }

        try {
            Category category = CatalogDelegate.findCategory(identifier);

            if (category == null) {
                JOptionPane.showMessageDialog(this,
                        "This category has not been found", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                model.setCategory(category);

                mainPane.setViewType(UPDATE_OR_DELETE);
            }
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }


    public void createActionPerformed(EventObject evt) {
        final String actionName = "create";

        CategoryModel model = mainPane.getModel();
        Category category = model.getCategory();

        try {
            category = CatalogDelegate.createCategory(category);

            dispose();
        } catch (Exception exc) {
            System.out.println("===============================");// TODO
            // virer les
            // traces
            exc.printStackTrace();
            System.out.println("===============================");
            EJBException e = (EJBException) exc;
            e.getCausedByException().printStackTrace();
            // displayException(className, actionName,
            // e.getCausedByException());//TODO jusque l�
            displayException(className, actionName, exc);
        }
    }

    public void readActionPerformed(EventObject evt) {
    }

    public void updateActionPerformed(EventObject evt) {
        final String actionName = "update";

        CategoryModel model = mainPane.getModel();
        Category category = model.getCategory();

        try {
            category = CatalogDelegate.updateCategory(category);

            dispose();
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }

    public void deleteActionPerformed(EventObject evt) {
        final String actionName = "delete";

        CategoryModel model = mainPane.getModel();
        Category category = model.getCategory();

        try {
            CatalogDelegate.deleteCategory(category);

            dispose();
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }


    public void resetActionPerformed(EventObject evt) {
        CategoryModel model = mainPane.getModel();
        YapsViewType viewType = mainPane.getViewType();

        if (viewType != FIND || viewType != CREATE
                || viewType != FIND_OR_CREATE) {
            model.setIdentifierToFind(null);
        }

        model.reset();
    }

}