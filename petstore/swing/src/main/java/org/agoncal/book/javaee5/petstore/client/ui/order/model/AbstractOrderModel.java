package org.agoncal.book.javaee5.petstore.client.ui.order.model;


import org.agoncal.book.javaee5.petstore.client.ui.order.event.OrderEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.order.event.OrderListener;
import org.agoncal.book.javaee5.petstore.entity.order.OrderLine;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;

import java.util.Date;
import java.util.List;

import static org.agoncal.book.javaee5.petstore.client.ui.order.event.OrderEventPropertyName.*;


public abstract class AbstractOrderModel extends
        AbstractXSModel<OrderListener, OrderEventPropertyName> implements
        OrderModel {

    @Override
    @SuppressWarnings("unchecked")
    protected void listenerMethodCaller(OrderListener listener,
                                        XSEvent<OrderEventPropertyName, ?> evt) {
        OrderEventPropertyName propertyName = evt.getPropertyNameEnumType();

        if (propertyName == IDENTIFIER_CHANGED) {
            listener
                    .identifierChanged((XSEvent<OrderEventPropertyName, Long>) evt);
        } else if (propertyName == ORDER_DATE_CHANGED) {
            listener
                    .orderDateChanged((XSEvent<OrderEventPropertyName, Date>) evt);
        } else if (propertyName == CREDIT_CARD_NUMBER_CHANGED) {
            listener
                    .creditCardNumberChanged((XSEvent<OrderEventPropertyName, String>) evt);
        } else if (propertyName == CREDIT_CARD_TYPE_CHANGED) {
            listener
                    .creditCardTypeChanged((XSEvent<OrderEventPropertyName, String>) evt);
        } else if (propertyName == CREDIT_CARD_EXPIRY_DATE_CHANGED) {
            listener
                    .creditCardExpiryDateChanged((XSEvent<OrderEventPropertyName, String>) evt);
        } else if (propertyName == ORDER_LINES_CHANGED) {
            listener
                    .orderLinesChanged((XSEvent<OrderEventPropertyName, List<OrderLine>>) evt);
        } else if (propertyName == TOTAL_CHANGED) {
            listener.totalChanged((XSEvent<OrderEventPropertyName, Float>) evt);
        }
    }

}