package org.agoncal.book.javaee5.petstore.client.ui.order.model;


import org.agoncal.book.javaee5.petstore.client.ui.common.address.model.AddressModel;
import org.agoncal.book.javaee5.petstore.client.ui.common.address.model.DefaultAddressModel;
import org.agoncal.book.javaee5.petstore.client.ui.customer.model.CustomerModel;
import org.agoncal.book.javaee5.petstore.client.ui.customer.model.DefaultCustomerModel;
import org.agoncal.book.javaee5.petstore.entity.Address;
import org.agoncal.book.javaee5.petstore.entity.customer.Customer;
import org.agoncal.book.javaee5.petstore.entity.order.CreditCard;
import org.agoncal.book.javaee5.petstore.entity.order.Order;
import org.agoncal.book.javaee5.petstore.entity.order.OrderLine;

import java.util.Date;
import java.util.List;

import static org.agoncal.book.javaee5.petstore.client.ui.order.event.OrderEventPropertyName.*;


public class DefaultOrderModel extends AbstractOrderModel {

    private static final long serialVersionUID = 1445431954238783287L;


    private CustomerModel customerModel;
    private AddressModel deliveryAddressModel;

    private Order order;
    private Long identifierToFind;


    public DefaultOrderModel() {
        Order order = new Order();
        Customer customer = new Customer();
        customer.setHomeAddress(new Address());
        order.setCustomer(customer);
        order.setDeliveryAddress(new Address());
        order.setCreditCard(new CreditCard());

        setOrder(order);
    }

    public DefaultOrderModel(Order order) {
        setOrder(order);
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("order must be non null");
        }
        if (order.getCreditCard() == null) {
            throw new IllegalArgumentException("credit card must be non null");
        }

        Order oldValue = this.order;
        Order newValue = order;

        this.order = order;

        if (oldValue != null) {
            fireXSChanged(this, IDENTIFIER_CHANGED, oldValue.getId(), newValue
                    .getId());
            fireXSChanged(this, ORDER_DATE_CHANGED, oldValue.getOrderDate(),
                    newValue.getOrderDate());
            fireXSChanged(this, CREDIT_CARD_NUMBER_CHANGED, oldValue
                    .getCreditCardNumber(), newValue.getCreditCardNumber());
            fireXSChanged(this, CREDIT_CARD_TYPE_CHANGED, oldValue
                    .getCreditCardType(), newValue.getCreditCardType());
            fireXSChanged(this, CREDIT_CARD_EXPIRY_DATE_CHANGED, oldValue
                    .getCreditCardExpiryDate(), newValue
                    .getCreditCardExpiryDate());
            fireXSChanged(this, ORDER_LINES_CHANGED, oldValue.getOrderLines(),
                    newValue.getOrderLines());
            fireXSChanged(this, TOTAL_CHANGED, oldValue.getTotal(), newValue
                    .getTotal());
        }

        if (customerModel == null) {
            customerModel = new DefaultCustomerModel(order.getCustomer());
        } else {
            customerModel.setCustomer(order.getCustomer());
        }

        if (deliveryAddressModel == null) {
            deliveryAddressModel = new DefaultAddressModel(order
                    .getDeliveryAddress());
        } else {
            deliveryAddressModel.setAddress(order.getDeliveryAddress());
        }
    }


    public Long getIdentifierToFind() {
        return identifierToFind;
    }

    public void setIdentifierToFind(Long identifierToFind) {
        Object oldValue = this.identifierToFind;
        Object newValue = identifierToFind;

        this.identifierToFind = identifierToFind;

        fireXSChanged(this, IDENTIFIER_CHANGED, oldValue, newValue);
    }

    public Long getIdentifier() {
        return order.getId();
    }


    public Date getOrderDate() {
        return order.getOrderDate();
    }

    public String getCreditCardNumber() {
        return order.getCreditCardNumber();
    }

    public void setCreditCardNumber(String creditCardNumber) {
        Object oldValue = order.getCreditCardNumber();
        Object newValue = creditCardNumber;

        order.setCreditCardNumber(creditCardNumber);

        fireXSChanged(this, CREDIT_CARD_NUMBER_CHANGED, oldValue, newValue);
    }

    public String getCreditCardType() {
        return order.getCreditCardType();
    }

    public void setCreditCardType(String creditCardType) {
        Object oldValue = order.getCreditCardType();
        Object newValue = creditCardType;

        order.setCreditCardType(creditCardType);

        fireXSChanged(this, CREDIT_CARD_TYPE_CHANGED, oldValue, newValue);
    }

    public String getCreditCardExpiryDate() {
        return order.getCreditCardExpiryDate();
    }

    public void setCreditCardExpiryDate(String creditCardExpiryDate) {
        Object oldValue = order.getCreditCardExpiryDate();
        Object newValue = creditCardExpiryDate;

        order.setCreditCardExpiryDate(creditCardExpiryDate);

        fireXSChanged(this, CREDIT_CARD_EXPIRY_DATE_CHANGED, oldValue, newValue);
    }

    public List<OrderLine> getOrderLines() {
        return order.getOrderLines();
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        Object oldValue = order.getOrderLines();
        Object newValue = orderLines;

        Float oldTotal = order.getTotal();

        order.setOrderLines(orderLines);

        fireXSChanged(this, ORDER_LINES_CHANGED, oldValue, newValue);

        updateTotal(oldTotal);
    }

    public Float getTotal() {
        return order.getTotal();
    }

    private void updateTotal(Float oldTotal) {
        Object oldValue = oldTotal;
        Object newValue = order.getTotal();

        fireXSChanged(this, TOTAL_CHANGED, oldValue, newValue);
    }


    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public AddressModel getDeliveryAddressModel() {
        return deliveryAddressModel;
    }


    public void reset() {
        setCreditCardNumber(null);
        setCreditCardType(null);
        setCreditCardExpiryDate(null);
        setOrderLines(null);

        customerModel.reset();
        deliveryAddressModel.reset();
    }

}