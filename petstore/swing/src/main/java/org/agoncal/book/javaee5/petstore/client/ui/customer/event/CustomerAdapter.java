package org.agoncal.book.javaee5.petstore.client.ui.customer.event;


import org.vstm.fwk.client.ui.xswing.core.event.XSAdapter;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import java.util.Date;


public class CustomerAdapter extends XSAdapter<CustomerEventPropertyName>
        implements CustomerListener {

    public void identifierChanged(XSEvent<CustomerEventPropertyName, Long> evt) {
    }

    public void firstNameChanged(XSEvent<CustomerEventPropertyName, String> evt) {
    }

    public void lastNameChanged(XSEvent<CustomerEventPropertyName, String> evt) {
    }

    public void dateOfBirthChanged(XSEvent<CustomerEventPropertyName, Date> evt) {
    }

    public void ageChanged(XSEvent<CustomerEventPropertyName, Integer> evt) {
    }

    public void telephoneChanged(XSEvent<CustomerEventPropertyName, String> evt) {
    }

    public void emailChanged(XSEvent<CustomerEventPropertyName, String> evt) {
    }

    public void loginChanged(XSEvent<CustomerEventPropertyName, String> evt) {
    }

    public void passwordChanged(XSEvent<CustomerEventPropertyName, String> evt) {
    }

}