package org.agoncal.book.javaee5.petstore.client.ui.common.address;


import org.agoncal.book.javaee5.petstore.client.ui.common.address.event.AddressAdapter;
import org.agoncal.book.javaee5.petstore.client.ui.common.address.event.AddressEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.common.address.event.AddressListener;
import org.agoncal.book.javaee5.petstore.client.ui.common.address.model.AddressModel;
import org.agoncal.book.javaee5.petstore.client.ui.common.address.model.DefaultAddressModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsComponentPane;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;
import org.apache.commons.lang.ObjectUtils;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.DELETE;
import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.READ;


public class AddressPane
        extends
        YapsComponentPane<AddressModel, AddressListener, AddressEventPropertyName> {

    private static final long serialVersionUID = -3015488093324282217L;


    private JTextField street1Field;
    private JTextField street2Field;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipcodeField;
    private JTextField countryField;


    public AddressPane() {
        super();
    }

    public AddressPane(AddressModel model) {
        super(model);
    }

    public AddressPane(YapsViewType viewType) {
        super(viewType);
    }

    public AddressPane(AddressModel model, YapsViewType viewType) {
        super(model, viewType);
    }


    @Override
    protected AddressModel createDefaultModel() {
        return new DefaultAddressModel();
    }

    @Override
    protected void initView() {
        street1Field = new JTextField();
        street2Field = new JTextField();
        cityField = new JTextField();
        stateField = new JTextField();
        zipcodeField = new JTextField();
        countryField = new JTextField();

        setLayout(new GridBagLayout());
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        int row = 0;
        Insets insets = new Insets(2, 5, 2, 5);

        add(new JLabel("Street 1"), new GridBagConstraints(0, row, 1, 1, 0.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                insets, 0, 0));
        add(street1Field, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(new JLabel("Street 2"), new GridBagConstraints(0, row, 1, 1, 0.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                insets, 0, 0));
        add(street2Field, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(new JLabel("City"), new GridBagConstraints(0, row, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
                0, 0));
        add(cityField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(new JLabel("State"), new GridBagConstraints(0, row, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
                0, 0));
        add(stateField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(new JLabel("Zipcode"), new GridBagConstraints(0, row, 1, 1, 0.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                insets, 0, 0));
        add(zipcodeField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(new JLabel("Country"), new GridBagConstraints(0, row, 1, 1, 0.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                insets, 0, 0));
        add(countryField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));


        synchronizeViewType(getViewType());
    }

    @Override
    protected void installViewListeners() {
        street1Field.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                String oldValue = model.getStreet1();
                String newValue = street1Field.getText();

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setStreet1(newValue);
                }
            }

        });

        street2Field.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                String oldValue = model.getStreet2();
                String newValue = street2Field.getText();

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setStreet2(newValue);
                }
            }

        });

        cityField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                String oldValue = model.getCity();
                String newValue = cityField.getText();

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setCity(newValue);
                }
            }

        });

        stateField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                String oldValue = model.getState();
                String newValue = stateField.getText();

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setState(newValue);
                }
            }

        });

        zipcodeField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                String oldValue = model.getZipcode();
                String newValue = zipcodeField.getText();

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setZipcode(newValue);
                }
            }

        });

        countryField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                String oldValue = model.getCountry();
                String newValue = countryField.getText();

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setCountry(newValue);
                }
            }

        });
    }

    @Override
    protected void initViewValues() {
        street1Field.setText(model.getStreet1());
        street2Field.setText(model.getStreet2());
        cityField.setText(model.getCity());
        stateField.setText(model.getState());
        zipcodeField.setText(model.getZipcode());
        countryField.setText(model.getCountry());
    }

    @Override
    protected AddressListener createDefaultPropertyChangeHandler() {
        return new PropertyChangeHandler();
    }


    private class PropertyChangeHandler extends AddressAdapter {

        @Override
        public void street1Changed(XSEvent<AddressEventPropertyName, String> evt) {
            String oldValue = street1Field.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                street1Field.setText(newValue);
            }
        }

        @Override
        public void street2Changed(XSEvent<AddressEventPropertyName, String> evt) {
            String oldValue = street2Field.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                street2Field.setText(newValue);
            }
        }

        @Override
        public void cityChanged(XSEvent<AddressEventPropertyName, String> evt) {
            String oldValue = cityField.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                cityField.setText(newValue);
            }
        }

        @Override
        public void stateChanged(XSEvent<AddressEventPropertyName, String> evt) {
            String oldValue = stateField.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                stateField.setText(newValue);
            }
        }

        @Override
        public void zipcodeChanged(XSEvent<AddressEventPropertyName, String> evt) {
            String oldValue = zipcodeField.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                zipcodeField.setText(newValue);
            }
        }

        @Override
        public void countryChanged(XSEvent<AddressEventPropertyName, String> evt) {
            String oldValue = countryField.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                countryField.setText(newValue);
            }
        }

    }


    @Override
    protected void synchronizeViewType(YapsViewType viewType) {
        street1Field.setEditable(viewType != READ && viewType != DELETE);
        street2Field.setEditable(viewType != READ && viewType != DELETE);
        cityField.setEditable(viewType != READ && viewType != DELETE);
        stateField.setEditable(viewType != READ && viewType != DELETE);
        zipcodeField.setEditable(viewType != READ && viewType != DELETE);
        countryField.setEditable(viewType != READ && viewType != DELETE);
    }

}