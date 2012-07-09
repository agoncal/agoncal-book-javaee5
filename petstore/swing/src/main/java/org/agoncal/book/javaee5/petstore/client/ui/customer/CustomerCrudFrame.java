package org.agoncal.book.javaee5.petstore.client.ui.customer;


import org.agoncal.book.javaee5.petstore.client.ui.customer.event.CustomerAdapter;
import org.agoncal.book.javaee5.petstore.client.ui.customer.event.CustomerEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.customer.model.CustomerModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;
import org.agoncal.book.javaee5.petstore.delegate.CustomerDelegate;
import org.agoncal.book.javaee5.petstore.entity.customer.Customer;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import javax.swing.*;
import java.util.EventObject;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.*;


public class CustomerCrudFrame extends YapsCrudFrame<CustomerPane> {

    private static final long serialVersionUID = -1175876732476090807L;


    public CustomerCrudFrame(final CustomerPane mainPane) {
        super(mainPane);

        mainPane.getModel().addXSListener(new CustomerAdapter() {

            @Override
            public void identifierChanged(
                    XSEvent<CustomerEventPropertyName, Long> evt) {
                initTitle(mainPane.getViewType());
            }

        });
    }


    public void findActionPerformed(EventObject evt) {
        final String actionName = "find";

        CustomerModel model = mainPane.getModel();
        Long identifier = model.getIdentifierToFind();

        if (identifier == null) {
            displayWarning("Identifier must be non null");

            return;
        }

        try {
            Customer customer = CustomerDelegate.findCustomer(identifier);

            if (customer == null) {
                JOptionPane.showMessageDialog(this,
                        "This customer has not been found", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                model.setCustomer(customer);

                mainPane.setViewType(UPDATE_OR_DELETE);
            }
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }


    public void createActionPerformed(EventObject evt) {
        final String actionName = "create";

        CustomerModel model = mainPane.getModel();
        Customer customer = model.getCustomer();

        try {
            customer = CustomerDelegate.createCustomer(customer, customer
                    .getHomeAddress());

            dispose();
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }

    public void readActionPerformed(EventObject evt) {
    }

    public void updateActionPerformed(EventObject evt) {
        final String actionName = "update";

        CustomerModel model = mainPane.getModel();
        Customer customer = model.getCustomer();

        try {
            customer = CustomerDelegate.updateCustomer(customer, customer
                    .getHomeAddress());

            dispose();
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }

    public void deleteActionPerformed(EventObject evt) {
        final String actionName = "delete";

        CustomerModel model = mainPane.getModel();
        Customer customer = model.getCustomer();

        try {
            CustomerDelegate.deleteCustomer(customer);

            dispose();
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }


    public void resetActionPerformed(EventObject evt) {
        CustomerModel model = mainPane.getModel();
        YapsViewType viewType = mainPane.getViewType();

        if (viewType != FIND || viewType != CREATE
                || viewType != FIND_OR_CREATE) {
            model.setIdentifierToFind(null);
        }

        model.reset();
    }

}