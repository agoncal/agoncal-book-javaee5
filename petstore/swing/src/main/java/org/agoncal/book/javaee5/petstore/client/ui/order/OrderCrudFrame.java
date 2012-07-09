package org.agoncal.book.javaee5.petstore.client.ui.order;


import org.agoncal.book.javaee5.petstore.client.ui.order.event.OrderAdapter;
import org.agoncal.book.javaee5.petstore.client.ui.order.event.OrderEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.order.model.OrderModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;
import org.agoncal.book.javaee5.petstore.delegate.OrderDelegate;
import org.agoncal.book.javaee5.petstore.entity.order.Order;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import javax.swing.*;
import java.util.EventObject;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.*;


public class OrderCrudFrame extends YapsCrudFrame<OrderPane> {

    private static final long serialVersionUID = 3371400413037437133L;


    public OrderCrudFrame(final OrderPane mainPane) {
        super(mainPane);

        mainPane.getModel().addXSListener(new OrderAdapter() {

            @Override
            public void identifierChanged(
                    XSEvent<OrderEventPropertyName, Long> evt) {
                initTitle(mainPane.getViewType());
            }

        });
    }


    public void findActionPerformed(EventObject evt) {
        final String actionName = "find";

        OrderModel model = mainPane.getModel();
        Long identifier = model.getIdentifierToFind();

        if (identifier == null) {
            displayWarning("Identifier must be non null");

            return;
        }

        try {
            Order order = OrderDelegate.findOrder(identifier);

            if (order == null) {
                JOptionPane.showMessageDialog(this,
                        "This order has not been found", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                model.setOrder(order);

                mainPane.setViewType(DELETE);
            }
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }


    public void createActionPerformed(EventObject evt) {
    }

    public void readActionPerformed(EventObject evt) {
    }

    public void updateActionPerformed(EventObject evt) {
    }

    public void deleteActionPerformed(EventObject evt) {
        final String actionName = "delete";

        OrderModel model = mainPane.getModel();
        Order order = model.getOrder();

        try {
            OrderDelegate.deleteOrder(order);

            dispose();
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }


    public void resetActionPerformed(EventObject evt) {
        OrderModel model = mainPane.getModel();
        YapsViewType viewType = mainPane.getViewType();

        if (viewType != FIND || viewType != CREATE
                || viewType != FIND_OR_CREATE) {
            model.setIdentifierToFind(null);
        }

        model.reset();
    }

}