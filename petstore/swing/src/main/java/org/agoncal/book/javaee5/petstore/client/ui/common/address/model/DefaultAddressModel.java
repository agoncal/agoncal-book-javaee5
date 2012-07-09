package org.agoncal.book.javaee5.petstore.client.ui.common.address.model;


import org.agoncal.book.javaee5.petstore.entity.Address;

import static org.agoncal.book.javaee5.petstore.client.ui.common.address.event.AddressEventPropertyName.*;


public class DefaultAddressModel extends AbstractAddressModel {

    private static final long serialVersionUID = 4412344911551538665L;


    private Address address;


    public DefaultAddressModel() {
        this(new Address());
    }

    public DefaultAddressModel(Address address) {
        setAddress(address);
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("address must be non null");
        }

        Address oldValue = this.address;
        Address newValue = address;

        this.address = address;

        if (oldValue != null) {
            fireXSChanged(this, STREET_1_CHANGED, oldValue.getStreet1(),
                    newValue.getStreet1());
            fireXSChanged(this, STREET_2_CHANGED, oldValue.getStreet2(),
                    newValue.getStreet2());
            fireXSChanged(this, CITY_CHANGED, oldValue.getCity(), newValue
                    .getCity());
            fireXSChanged(this, STATE_CHANGED, oldValue.getState(), newValue
                    .getState());
            fireXSChanged(this, ZIPCODE_CHANGED, oldValue.getZipcode(),
                    newValue.getZipcode());
            fireXSChanged(this, COUNTRY_CHANGED, oldValue.getCountry(),
                    newValue.getCountry());
        }
    }


    public String getStreet1() {
        return address.getStreet1();
    }

    public void setStreet1(String street1) {
        Object oldValue = address.getStreet1();
        Object newValue = street1;

        address.setStreet1(street1);

        fireXSChanged(this, STREET_1_CHANGED, oldValue, newValue);
    }

    public String getStreet2() {
        return address.getStreet2();
    }

    public void setStreet2(String street2) {
        Object oldValue = address.getStreet2();
        Object newValue = street2;

        address.setStreet2(street2);

        fireXSChanged(this, STREET_2_CHANGED, oldValue, newValue);
    }

    public String getCity() {
        return address.getCity();
    }

    public void setCity(String city) {
        Object oldValue = address.getCity();
        Object newValue = city;

        address.setCity(city);

        fireXSChanged(this, CITY_CHANGED, oldValue, newValue);
    }

    public String getState() {
        return address.getState();
    }

    public void setState(String state) {
        Object oldValue = address.getState();
        Object newValue = state;

        address.setState(state);

        fireXSChanged(this, STATE_CHANGED, oldValue, newValue);
    }

    public String getZipcode() {
        return address.getZipcode();
    }

    public void setZipcode(String zipcode) {
        Object oldValue = address.getZipcode();
        Object newValue = zipcode;

        address.setZipcode(zipcode);

        fireXSChanged(this, ZIPCODE_CHANGED, oldValue, newValue);
    }

    public String getCountry() {
        return address.getCountry();
    }

    public void setCountry(String country) {
        Object oldValue = address.getCountry();
        Object newValue = country;

        address.setCountry(country);

        fireXSChanged(this, COUNTRY_CHANGED, oldValue, newValue);
    }


    public void reset() {
        setStreet1(null);
        setStreet2(null);
        setCity(null);
        setState(null);
        setZipcode(null);
        setCountry(null);
    }

}