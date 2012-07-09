package org.agoncal.book.javaee5.petstore.client.ui.customer.model;


import org.agoncal.book.javaee5.petstore.client.ui.common.address.model.AddressModel;
import org.agoncal.book.javaee5.petstore.client.ui.customer.event.CustomerEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.customer.event.CustomerListener;
import org.agoncal.book.javaee5.petstore.entity.customer.Customer;
import org.vstm.fwk.client.ui.xswing.core.model.XSModel;

import java.util.Date;


public interface CustomerModel extends
        XSModel<CustomerListener, CustomerEventPropertyName> {

    public Customer getCustomer();

    public void setCustomer(Customer customer);


    public Long getIdentifierToFind();

    public void setIdentifierToFind(Long identifier);

    public Long getIdentifier();


    public String getFirstName();

    public void setFirstName(String firstName);


    public String getLastName();

    public void setLastName(String lastName);


    public Date getDateOfBirth();

    public void setDateOfBirth(Date dateOfBirth);


    public Integer getAge();


    public String getTelephone();

    public void setTelephone(String telephone);


    public String getEmail();

    public void setEmail(String email);


    public String getLogin();

    public void setLogin(String login);


    public String getPassword();

    public void setPassword(String password);


    public AddressModel getAddressModel();

}