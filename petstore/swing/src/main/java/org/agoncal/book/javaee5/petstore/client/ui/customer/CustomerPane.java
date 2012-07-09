package org.agoncal.book.javaee5.petstore.client.ui.customer;


import org.agoncal.book.javaee5.petstore.client.ui.common.address.AddressPane;
import org.agoncal.book.javaee5.petstore.client.ui.customer.event.CustomerAdapter;
import org.agoncal.book.javaee5.petstore.client.ui.customer.event.CustomerEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.customer.event.CustomerListener;
import org.agoncal.book.javaee5.petstore.client.ui.customer.model.CustomerModel;
import org.agoncal.book.javaee5.petstore.client.ui.customer.model.DefaultCustomerModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsComponentPane;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;
import org.apache.commons.lang.ObjectUtils;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Date;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsUIConstants.DATE_PATTERN;
import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsUIConstants.DATE_PATTERNS;
import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.*;
import static org.apache.commons.lang.time.DateFormatUtils.format;
import static org.apache.commons.lang.time.DateUtils.parseDate;


public class CustomerPane
        extends
        YapsComponentPane<CustomerModel, CustomerListener, CustomerEventPropertyName> {

    private static final long serialVersionUID = 1418503926208288280L;


    private boolean areMinimumInfoDisplayed;

    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField dateOfBirthField;
    private JTextField ageField;
    private JTextField telephoneField;
    private JTextField emailField;
    private JTextField loginField;
    private JPasswordField passwordField;

    private AddressPane addressPane;


    public CustomerPane() {
    }

    public CustomerPane(CustomerModel model) {
        super(model);
    }

    public CustomerPane(CustomerModel model, boolean areMinimumInfoDisplayed) {
        super(model);

        this.areMinimumInfoDisplayed = areMinimumInfoDisplayed;
    }

    public CustomerPane(YapsViewType viewType) {
        super(viewType);
    }

    public CustomerPane(CustomerModel model, YapsViewType viewType) {
        super(model, viewType);
    }


    @Override
    protected CustomerModel createDefaultModel() {
        return new DefaultCustomerModel();
    }

    @Override
    protected void initView() {
        idField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        dateOfBirthField = new JTextField();
        ageField = new JTextField();
        telephoneField = new JTextField();
        emailField = new JTextField();
        loginField = new JTextField();
        passwordField = new JPasswordField();

        addressPane = new AddressPane(getModel().getAddressModel());
        addressPane.setBorder(BorderFactory.createTitledBorder("Address"));


        JPanel identificationPane = new JPanel(new GridBagLayout());
        identificationPane.setOpaque(false);
        identificationPane.setBorder(BorderFactory
                .createTitledBorder("Identification"));

        int row = 0;
        Insets insets = new Insets(2, 5, 2, 5);

        identificationPane.add(new JLabel("Login"), new GridBagConstraints(0,
                row, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.VERTICAL, insets, 0, 0));
        identificationPane.add(loginField, new GridBagConstraints(1, row++, 1,
                1, 1.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, insets, 0, 0));

        identificationPane.add(new JLabel("Password"), new GridBagConstraints(
                0, row, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.VERTICAL, insets, 0, 0));
        identificationPane.add(passwordField, new GridBagConstraints(1, row++,
                1, 1, 1.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, insets, 0, 0));


        ageField.setEditable(false);


        setLayout(new GridBagLayout());
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        row = 0;
        if (!areMinimumInfoDisplayed) {
            add(new JLabel("Identifier"), new GridBagConstraints(0, row, 1, 1,
                    0.0, 0.0, GridBagConstraints.WEST,
                    GridBagConstraints.VERTICAL, insets, 0, 0));
            add(idField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                    insets, 0, 0));
        }

        add(new JLabel("First Name"), new GridBagConstraints(0, row, 1, 1, 0.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                insets, 0, 0));
        add(firstNameField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(new JLabel("Last Name"), new GridBagConstraints(0, row, 1, 1, 0.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                insets, 0, 0));
        add(lastNameField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(new JLabel("Date of birth"), new GridBagConstraints(0, row, 1, 1,
                0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                insets, 0, 0));
        add(dateOfBirthField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        if (!areMinimumInfoDisplayed) {
            add(new JLabel("Age"), new GridBagConstraints(0, row, 1, 1, 0.0,
                    0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    insets, 0, 0));
            add(ageField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                    insets, 0, 0));

            add(new JLabel("Telephone"), new GridBagConstraints(0, row, 1, 1,
                    0.0, 0.0, GridBagConstraints.WEST,
                    GridBagConstraints.VERTICAL, insets, 0, 0));
            add(telephoneField, new GridBagConstraints(1, row++, 1, 1, 1.0,
                    0.0, GridBagConstraints.WEST,
                    GridBagConstraints.HORIZONTAL, insets, 0, 0));

            add(new JLabel("Email"), new GridBagConstraints(0, row, 1, 1, 0.0,
                    0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                    insets, 0, 0));
            add(emailField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                    insets, 0, 0));

            add(identificationPane, new GridBagConstraints(0, row++, 2, 1, 1.0,
                    0.0, GridBagConstraints.WEST,
                    GridBagConstraints.HORIZONTAL, insets, 0, 0));
        }

        add(addressPane, new GridBagConstraints(0, row++, 2, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));


        synchronizeViewType(getViewType());
    }

    @Override
    protected void installViewListeners() {
        idField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent evt) {
                Long oldValue = model.getIdentifierToFind();
                Long newValue = null;

                try {
                    newValue = new Long(idField.getText());
                    idField.setForeground(Color.black);
                } catch (NumberFormatException exc) {
                    newValue = oldValue;
                    idField.setForeground(Color.red);
                }

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setIdentifierToFind(newValue);
                }
            }

        });

        idField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                Long oldValue = model.getIdentifierToFind();
                Long newValue = null;

                try {
                    newValue = new Long(idField.getText());
                    idField.setForeground(Color.black);
                } catch (NumberFormatException exc) {
                    newValue = oldValue;
                    idField.setForeground(Color.red);
                }

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setIdentifierToFind(newValue);
                }
            }

        });

        firstNameField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                String oldValue = model.getFirstName();
                String newValue = firstNameField.getText();

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setFirstName(newValue);
                }
            }

        });

        lastNameField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                String oldValue = model.getLastName();
                String newValue = lastNameField.getText();

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setLastName(newValue);
                }
            }

        });

        dateOfBirthField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                Date oldValue = model.getDateOfBirth();
                Date newValue = null;

                try {
                    newValue = parseDate(dateOfBirthField.getText(),
                            DATE_PATTERNS);

                    dateOfBirthField.setForeground(Color.black);

                    if (!ObjectUtils.equals(oldValue, newValue)) {
                        model.setDateOfBirth(newValue);
                    }
                } catch (ParseException exc) {
                    dateOfBirthField.setForeground(Color.red);
                }
            }

        });

        telephoneField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                String oldValue = model.getTelephone();
                String newValue = telephoneField.getText();

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setTelephone(newValue);
                }
            }

        });

        emailField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                String oldValue = model.getEmail();
                String newValue = emailField.getText();

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setEmail(newValue);
                }
            }

        });

        loginField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                String oldValue = model.getLogin();
                String newValue = loginField.getText();

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setLogin(newValue);
                }
            }

        });

        passwordField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                String oldValue = model.getPassword();
                String newValue = (passwordField.getPassword() == null ? null
                        : String.valueOf(passwordField.getPassword()));

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setPassword(newValue);
                }
            }

        });

    }

    @Override
    protected void initViewValues() {
        idField.setText(model.getIdentifier() == null ? null : model
                .getIdentifier().toString());
        firstNameField.setText(model.getFirstName());
        lastNameField.setText(model.getLastName());

        Date dateOfBirth = model.getDateOfBirth();
        if (dateOfBirth == null) {
            dateOfBirthField.setText(null);
        } else {
            dateOfBirthField.setText(format(dateOfBirth, DATE_PATTERN));
        }

        ageField.setText(model.getAge() == null ? null : model.getAge()
                .toString());
        telephoneField.setText(model.getTelephone());
        emailField.setText(model.getEmail());
        loginField.setText(model.getLogin());
        passwordField.setText(model.getPassword());

        if (addressPane.getModel() != model.getAddressModel()) {
            addressPane.setModel(model.getAddressModel());
        }
    }

    @Override
    protected CustomerListener createDefaultPropertyChangeHandler() {
        return new PropertyChangeHandler();
    }


    private class PropertyChangeHandler extends CustomerAdapter {

        @Override
        public void identifierChanged(
                XSEvent<CustomerEventPropertyName, Long> evt) {
            Long oldValue = null;

            try {
                oldValue = new Long(idField.getText());
            } catch (Exception exc) {
            }

            Long newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                idField.setText(newValue == null ? null : newValue.toString());
            }
        }

        @Override
        public void firstNameChanged(
                XSEvent<CustomerEventPropertyName, String> evt) {
            String oldValue = firstNameField.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                firstNameField.setText(newValue);
            }
        }

        @Override
        public void lastNameChanged(
                XSEvent<CustomerEventPropertyName, String> evt) {
            String oldValue = lastNameField.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                lastNameField.setText(newValue);
            }
        }

        @Override
        public void dateOfBirthChanged(
                XSEvent<CustomerEventPropertyName, Date> evt) {
            Date oldValue = null;

            try {
                oldValue = parseDate(dateOfBirthField.getText(), DATE_PATTERNS);
            } catch (ParseException exc) {
                return;
            }

            Date newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                if (newValue == null) {
                    dateOfBirthField.setText(null);
                } else {
                    dateOfBirthField.setText(format(newValue, DATE_PATTERN));
                }
            }
        }

        @Override
        public void ageChanged(XSEvent<CustomerEventPropertyName, Integer> evt) {
            Integer oldValue = null;

            try {
                oldValue = new Integer(ageField.getText());
            } catch (Exception exc) {
            }

            Integer newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                ageField.setText(newValue == null ? null : newValue.toString());
            }
        }

        @Override
        public void telephoneChanged(
                XSEvent<CustomerEventPropertyName, String> evt) {
            String oldValue = telephoneField.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                telephoneField.setText(newValue);
            }
        }

        @Override
        public void emailChanged(XSEvent<CustomerEventPropertyName, String> evt) {
            String oldValue = emailField.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                emailField.setText(newValue);
            }
        }

        @Override
        public void loginChanged(XSEvent<CustomerEventPropertyName, String> evt) {
            String oldValue = loginField.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                loginField.setText(newValue);
            }
        }

        @Override
        public void passwordChanged(
                XSEvent<CustomerEventPropertyName, String> evt) {
            String oldValue = new String(passwordField.getPassword());
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                passwordField.setText(newValue);
            }
        }

    }


    @Override
    protected void synchronizeViewType(YapsViewType viewType) {
        idField.setEditable(viewType == FIND || viewType == CREATE
                || viewType == FIND_OR_CREATE);
        firstNameField.setEditable(viewType != READ && viewType != DELETE);
        lastNameField.setEditable(viewType != READ && viewType != DELETE);
        dateOfBirthField.setEditable(viewType != READ && viewType != DELETE);
        telephoneField.setEditable(viewType != READ && viewType != DELETE);
        emailField.setEditable(viewType != READ && viewType != DELETE);
        loginField.setEditable(viewType != READ && viewType != DELETE);
        passwordField.setEditable(viewType != READ && viewType != DELETE);

        addressPane.setViewType(viewType);
    }

    @Override
    public String toString() {
        String text = "Customer";

        if (model.getIdentifier() != null && !model.getIdentifier().equals("")) {
            text += " - " + model.getIdentifier();
        }

        return text;
    }


    public boolean areMinimumInfoDisplayed() {
        return areMinimumInfoDisplayed;
    }

}