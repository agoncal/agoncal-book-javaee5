package org.agoncal.book.javaee5.petstore.client.ui.common.address.model;


import org.agoncal.book.javaee5.petstore.client.ui.common.address.event.AddressEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.common.address.event.AddressListener;
import org.agoncal.book.javaee5.petstore.entity.Address;
import org.vstm.fwk.client.ui.xswing.core.model.XSModel;


public interface AddressModel extends
        XSModel<AddressListener, AddressEventPropertyName> {

    public Address getAddress();

    public void setAddress(Address address);


    public String getStreet1();

    public void setStreet1(String street1);

    public String getStreet2();

    public void setStreet2(String street2);

    public String getCity();

    public void setCity(String city);

    public String getState();

    public void setState(String state);

    public String getZipcode();

    public void setZipcode(String zipCode);

    public String getCountry();

    public void setCountry(String country);

}