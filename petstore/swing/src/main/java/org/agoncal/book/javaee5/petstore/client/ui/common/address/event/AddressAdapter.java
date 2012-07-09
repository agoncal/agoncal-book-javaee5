package org.agoncal.book.javaee5.petstore.client.ui.common.address.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSAdapter;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;


public class AddressAdapter extends XSAdapter<AddressEventPropertyName>
        implements AddressListener {

    public void street1Changed(XSEvent<AddressEventPropertyName, String> evt) {
    }

    public void street2Changed(XSEvent<AddressEventPropertyName, String> evt) {
    }

    public void cityChanged(XSEvent<AddressEventPropertyName, String> evt) {
    }

    public void stateChanged(XSEvent<AddressEventPropertyName, String> evt) {
    }

    public void zipcodeChanged(XSEvent<AddressEventPropertyName, String> evt) {
    }

    public void countryChanged(XSEvent<AddressEventPropertyName, String> evt) {
    }

}