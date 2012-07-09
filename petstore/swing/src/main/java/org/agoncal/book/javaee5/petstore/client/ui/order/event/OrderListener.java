package org.agoncal.book.javaee5.petstore.client.ui.order.event;


import org.agoncal.book.javaee5.petstore.entity.order.OrderLine;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;

import java.util.Date;
import java.util.List;


public interface OrderListener extends XSListener<OrderEventPropertyName> {

    // ------------------------------------------------------------------------
    // 
    public void identifierChanged(XSEvent<OrderEventPropertyName, Long> evt);

    public void orderDateChanged(XSEvent<OrderEventPropertyName, Date> evt);

    public void creditCardNumberChanged(
            XSEvent<OrderEventPropertyName, String> evt);

    public void creditCardTypeChanged(
            XSEvent<OrderEventPropertyName, String> evt);

    public void creditCardExpiryDateChanged(
            XSEvent<OrderEventPropertyName, String> evt);

    public void orderLinesChanged(
            XSEvent<OrderEventPropertyName, List<OrderLine>> evt);

    public void totalChanged(XSEvent<OrderEventPropertyName, Float> evt);

}