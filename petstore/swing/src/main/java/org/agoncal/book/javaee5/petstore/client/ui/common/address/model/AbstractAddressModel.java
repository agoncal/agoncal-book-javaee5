package org.agoncal.book.javaee5.petstore.client.ui.common.address.model;


import org.agoncal.book.javaee5.petstore.client.ui.common.address.event.AddressEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.common.address.event.AddressListener;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;

import static org.agoncal.book.javaee5.petstore.client.ui.common.address.event.AddressEventPropertyName.*;


public abstract class AbstractAddressModel extends
        AbstractXSModel<AddressListener, AddressEventPropertyName> implements
        AddressModel {

    @Override
    @SuppressWarnings("unchecked")
    protected void listenerMethodCaller(AddressListener listener,
                                        XSEvent<AddressEventPropertyName, ?> evt) {
        AddressEventPropertyName propertyName = evt.getPropertyNameEnumType();

        if (propertyName == STREET_1_CHANGED) {
            listener
                    .street1Changed((XSEvent<AddressEventPropertyName, String>) evt);
        } else if (propertyName == STREET_2_CHANGED) {
            listener
                    .street2Changed((XSEvent<AddressEventPropertyName, String>) evt);
        } else if (propertyName == CITY_CHANGED) {
            listener
                    .cityChanged((XSEvent<AddressEventPropertyName, String>) evt);
        } else if (propertyName == STATE_CHANGED) {
            listener
                    .stateChanged((XSEvent<AddressEventPropertyName, String>) evt);
        } else if (propertyName == ZIPCODE_CHANGED) {
            listener
                    .zipcodeChanged((XSEvent<AddressEventPropertyName, String>) evt);
        } else if (propertyName == COUNTRY_CHANGED) {
            listener
                    .countryChanged((XSEvent<AddressEventPropertyName, String>) evt);
        }
    }

}