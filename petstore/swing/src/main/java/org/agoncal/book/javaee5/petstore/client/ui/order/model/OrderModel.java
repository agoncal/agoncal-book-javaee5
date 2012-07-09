package org.agoncal.book.javaee5.petstore.client.ui.order.model;


import org.agoncal.book.javaee5.petstore.client.ui.common.address.model.AddressModel;
import org.agoncal.book.javaee5.petstore.client.ui.customer.model.CustomerModel;
import org.agoncal.book.javaee5.petstore.client.ui.order.event.OrderEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.order.event.OrderListener;
import org.agoncal.book.javaee5.petstore.entity.order.Order;
import org.agoncal.book.javaee5.petstore.entity.order.OrderLine;
import org.vstm.fwk.client.ui.xswing.core.model.XSModel;

import java.util.Date;
import java.util.List;


public interface OrderModel extends
        XSModel<OrderListener, OrderEventPropertyName> {

    public Order getOrder();

    public void setOrder(Order order);


    public Long getIdentifierToFind();

    public void setIdentifierToFind(Long identifierToFind);

    public Long getIdentifier();


    public Date getOrderDate();


    public String getCreditCardNumber();

    public void setCreditCardNumber(String creditCardNumber);


    public String getCreditCardType();

    public void setCreditCardType(String creditCardType);


    public String getCreditCardExpiryDate();

    public void setCreditCardExpiryDate(String creditCardExpiryDate);


    public List<OrderLine> getOrderLines();

    public void setOrderLines(List<OrderLine> orderLines);


    public Float getTotal();


    public CustomerModel getCustomerModel();

    public AddressModel getDeliveryAddressModel();

}