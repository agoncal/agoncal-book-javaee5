package org.agoncal.book.javaee5.petstore.client.ui.customer.model;


import org.agoncal.book.javaee5.petstore.client.ui.common.address.model.AddressModel;
import org.agoncal.book.javaee5.petstore.client.ui.common.address.model.DefaultAddressModel;
import org.agoncal.book.javaee5.petstore.entity.Address;
import org.agoncal.book.javaee5.petstore.entity.customer.Customer;

import java.util.Date;

import static org.agoncal.book.javaee5.petstore.client.ui.customer.event.CustomerEventPropertyName.*;


public class DefaultCustomerModel extends AbstractCustomerModel {

    private static final long serialVersionUID = 6117458605996580214L;


    private Customer customer;
    private Long identifierToFind;

    private AddressModel addressModel;


    public DefaultCustomerModel() {
        Customer customer = new Customer();
        customer.setHomeAddress(new Address());

        setCustomer(customer);
    }

    public DefaultCustomerModel(Customer customer) {
        setCustomer(customer);
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer must be non null");
        }

        Customer oldValue = this.customer;
        Customer newValue = customer;

        this.customer = customer;

        if (oldValue != null) {
            fireXSChanged(this, IDENTIFIER_CHANGED, oldValue.getId(), newValue
                    .getId());
            fireXSChanged(this, FIRST_NAME_CHANGED, oldValue.getFirstname(),
                    newValue.getFirstname());
            fireXSChanged(this, LAST_NAME_CHANGED, oldValue.getLastname(),
                    newValue.getLastname());
            fireXSChanged(this, TELEPHONE_CHANGED, oldValue.getTelephone(),
                    newValue.getTelephone());
            fireXSChanged(this, EMAIL_CHANGED, oldValue.getEmail(), newValue
                    .getEmail());
        }

        if (addressModel == null) {
            addressModel = new DefaultAddressModel(customer.getHomeAddress());
        } else {
            addressModel.setAddress(customer.getHomeAddress());
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
        return customer.getId();
    }


    public String getFirstName() {
        return customer.getFirstname();
    }

    public void setFirstName(String firstName) {
        Object oldValue = customer.getFirstname();
        Object newValue = firstName;

        customer.setFirstname(firstName);

        fireXSChanged(this, FIRST_NAME_CHANGED, oldValue, newValue);
    }

    public String getLastName() {
        return customer.getLastname();
    }

    public void setLastName(String lastName) {
        Object oldValue = customer.getLastname();
        Object newValue = lastName;

        customer.setLastname(lastName);

        fireXSChanged(this, LAST_NAME_CHANGED, oldValue, newValue);
    }

    public Date getDateOfBirth() {
        return customer.getDateOfBirth();
    }

    public void setDateOfBirth(Date dateOfBirth) {
        Object oldValue = customer.getDateOfBirth();
        Object newValue = dateOfBirth;

        customer.setDateOfBirth(dateOfBirth);

        fireXSChanged(this, DATE_OF_BIRTH_CHANGED, oldValue, newValue);

        // When the date of birth is changed, the age can be updated.
        updateAge();
    }

    public Integer getAge() {
        return customer.getAge();
    }

    private void updateAge() {
        Object oldValue = customer.getAge();
        customer.calculateAge();
        Object newValue = customer.getAge();

        fireXSChanged(this, AGE_CHANGED, oldValue, newValue);
    }

    public String getTelephone() {
        return customer.getTelephone();
    }

    public void setTelephone(String telephone) {
        Object oldValue = customer.getTelephone();
        Object newValue = telephone;

        customer.setTelephone(telephone);

        fireXSChanged(this, TELEPHONE_CHANGED, oldValue, newValue);
    }

    public String getEmail() {
        return customer.getEmail();
    }

    public void setEmail(String email) {
        Object oldValue = customer.getEmail();
        Object newValue = email;

        customer.setEmail(email);

        fireXSChanged(this, EMAIL_CHANGED, oldValue, newValue);
    }

    public String getLogin() {
        return customer.getLogin();
    }

    public void setLogin(String login) {
        Object oldValue = customer.getLogin();
        Object newValue = login;

        customer.setLogin(login);

        fireXSChanged(this, LOGIN_CHANGED, oldValue, newValue);
    }

    public String getPassword() {
        return customer.getPassword();
    }

    public void setPassword(String password) {
        Object oldValue = customer.getPassword();
        Object newValue = password;

        customer.setPassword(password);

        fireXSChanged(this, PASSWORD_CHANGED, oldValue, newValue);
    }


    public AddressModel getAddressModel() {
        return addressModel;
    }


    public void reset() {
        setFirstName(null);
        setLastName(null);
        setDateOfBirth(null);
        setTelephone(null);
        setEmail(null);
        setLogin(null);
        setPassword(null);

        addressModel.reset();
    }

}