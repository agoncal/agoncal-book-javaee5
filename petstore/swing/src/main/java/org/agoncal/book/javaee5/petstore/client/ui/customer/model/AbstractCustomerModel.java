package org.agoncal.book.javaee5.petstore.client.ui.customer.model;


import org.agoncal.book.javaee5.petstore.client.ui.customer.event.CustomerEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.customer.event.CustomerListener;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;
import org.vstm.fwk.client.ui.xswing.core.model.AbstractXSModel;

import java.util.Date;

import static org.agoncal.book.javaee5.petstore.client.ui.customer.event.CustomerEventPropertyName.*;


public abstract class AbstractCustomerModel extends
        AbstractXSModel<CustomerListener, CustomerEventPropertyName> implements
        CustomerModel {


    @Override
    @SuppressWarnings("unchecked")
    protected void listenerMethodCaller(CustomerListener listener,
                                        XSEvent<CustomerEventPropertyName, ?> evt) {
        CustomerEventPropertyName propertyName = evt.getPropertyNameEnumType();

        if (propertyName == IDENTIFIER_CHANGED) {
            listener
                    .identifierChanged((XSEvent<CustomerEventPropertyName, Long>) evt);
        } else if (propertyName == FIRST_NAME_CHANGED) {
            listener
                    .firstNameChanged((XSEvent<CustomerEventPropertyName, String>) evt);
        } else if (propertyName == LAST_NAME_CHANGED) {
            listener
                    .lastNameChanged((XSEvent<CustomerEventPropertyName, String>) evt);
        } else if (propertyName == DATE_OF_BIRTH_CHANGED) {
            listener
                    .dateOfBirthChanged((XSEvent<CustomerEventPropertyName, Date>) evt);
        } else if (propertyName == AGE_CHANGED) {
            listener
                    .ageChanged((XSEvent<CustomerEventPropertyName, Integer>) evt);
        } else if (propertyName == TELEPHONE_CHANGED) {
            listener
                    .telephoneChanged((XSEvent<CustomerEventPropertyName, String>) evt);
        } else if (propertyName == EMAIL_CHANGED) {
            listener
                    .emailChanged((XSEvent<CustomerEventPropertyName, String>) evt);
        } else if (propertyName == LOGIN_CHANGED) {
            listener
                    .loginChanged((XSEvent<CustomerEventPropertyName, String>) evt);
        } else if (propertyName == PASSWORD_CHANGED) {
            listener
                    .passwordChanged((XSEvent<CustomerEventPropertyName, String>) evt);
        }
    }

}