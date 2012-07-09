package org.agoncal.book.javaee5.petstore.client.ui.order;


import org.agoncal.book.javaee5.petstore.client.ui.common.address.AddressPane;
import org.agoncal.book.javaee5.petstore.client.ui.customer.CustomerPane;
import org.agoncal.book.javaee5.petstore.client.ui.order.event.OrderAdapter;
import org.agoncal.book.javaee5.petstore.client.ui.order.event.OrderEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.order.event.OrderListener;
import org.agoncal.book.javaee5.petstore.client.ui.order.model.DefaultOrderModel;
import org.agoncal.book.javaee5.petstore.client.ui.order.model.OrderLineTableModel;
import org.agoncal.book.javaee5.petstore.client.ui.order.model.OrderModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsComponentPane;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsTable;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;
import org.agoncal.book.javaee5.petstore.entity.order.OrderLine;
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
import java.util.List;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsUIConstants.DATE_PATTERN;
import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsUIConstants.DATE_PATTERNS;
import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.*;
import static org.apache.commons.lang.time.DateFormatUtils.format;
import static org.apache.commons.lang.time.DateUtils.parseDate;


public class OrderPane extends
        YapsComponentPane<OrderModel, OrderListener, OrderEventPropertyName> {

    private static final long serialVersionUID = -878876420341644261L;


    private JTextField textId;
    private JTextField textOrderDate;
    private JTextField textCreditCardNumber;
    private JTextField textCreditCardType;
    private JTextField textCreditCardExpiryDate;
    private YapsTable tableOrderLines;
    private JTextField textTotal;

    private CustomerPane customerPane;
    private AddressPane deliveryAddressPane;


    public OrderPane() {
        super();
    }

    public OrderPane(OrderModel model) {
        super(model);
    }

    public OrderPane(YapsViewType viewType) {
        super(viewType);
    }

    public OrderPane(OrderModel model, YapsViewType viewType) {
        super(model, viewType);
    }


    @Override
    protected OrderModel createDefaultModel() {
        return new DefaultOrderModel();
    }

    @Override
    protected void initView() {
        textId = new JTextField();
        textOrderDate = new JTextField();
        textCreditCardNumber = new JTextField();
        textCreditCardType = new JTextField();
        textCreditCardExpiryDate = new JTextField();
        textTotal = new JTextField();

        customerPane = new CustomerPane(model.getCustomerModel(), true);
        customerPane.setBorder(BorderFactory.createTitledBorder("Customer"));
        customerPane.setPreferredSize(new Dimension(250, (int) customerPane
                .getPreferredSize().getHeight()));

        deliveryAddressPane = new AddressPane(model.getDeliveryAddressModel());
        deliveryAddressPane.setBorder(BorderFactory
                .createTitledBorder("Delivery Address"));

        tableOrderLines = new YapsTable(new OrderLineTableModel(model
                .getOrderLines()));
        JScrollPane scrollingTable = new JScrollPane(tableOrderLines,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollingTable.setPreferredSize(new Dimension(500, 145));


        JPanel creditCardPane = new JPanel(new GridBagLayout());
        creditCardPane.setOpaque(false);
        creditCardPane.setBorder(BorderFactory
                .createTitledBorder("Credit card"));

        int row = 0;
        Insets insets = new Insets(2, 5, 2, 5);

        creditCardPane.add(new JLabel("Number"), new GridBagConstraints(0, row,
                1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.VERTICAL, insets, 0, 0));
        creditCardPane.add(textCreditCardNumber, new GridBagConstraints(1,
                row++, 1, 1, 1.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, insets, 0, 0));

        creditCardPane.add(new JLabel("Type"), new GridBagConstraints(0, row,
                1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.VERTICAL, insets, 0, 0));
        creditCardPane.add(textCreditCardType, new GridBagConstraints(1, row++,
                1, 1, 1.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, insets, 0, 0));

        creditCardPane.add(new JLabel("Expiry date"), new GridBagConstraints(0,
                row, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.VERTICAL, insets, 0, 0));
        creditCardPane.add(textCreditCardExpiryDate, new GridBagConstraints(1,
                row++, 1, 1, 1.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.HORIZONTAL, insets, 0, 0));


        setLayout(new GridBagLayout());
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        row = 0;

        add(new JLabel("Identifier"), new GridBagConstraints(0, row, 1, 1, 0.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                insets, 0, 0));
        add(textId, new GridBagConstraints(1, row, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(scrollingTable, new GridBagConstraints(2, row++, 3, 3, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(new JLabel("Order date"), new GridBagConstraints(0, row, 1, 1, 0.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                insets, 0, 0));
        add(textOrderDate, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(customerPane, new GridBagConstraints(0, row++, 2, 3, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(deliveryAddressPane, new GridBagConstraints(2, row, 1, 2, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(new JLabel("Total"), new GridBagConstraints(3, row, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
                0, 0));
        add(textTotal, new GridBagConstraints(4, row++, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(creditCardPane, new GridBagConstraints(3, row++, 2, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));


        synchronizeViewType(getViewType());
    }

    @Override
    protected void installViewListeners() {
        textId.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent evt) {
                Long oldValue = model.getIdentifierToFind();
                Long newValue = null;

                try {
                    newValue = new Long(textId.getText());
                    textId.setForeground(Color.black);
                } catch (NumberFormatException exc) {
                    newValue = oldValue;
                    textId.setForeground(Color.red);
                }

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setIdentifierToFind(newValue);
                }
            }

        });

        textId.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                Long oldValue = model.getIdentifierToFind();
                Long newValue = null;

                try {
                    newValue = new Long(textId.getText());
                    textId.setForeground(Color.black);
                } catch (NumberFormatException exc) {
                    newValue = oldValue;
                    textId.setForeground(Color.red);
                }

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setIdentifierToFind(newValue);
                }
            }

        });
    }

    @Override
    protected void initViewValues() {
        textId.setText(model.getIdentifier() == null ? null : model
                .getIdentifier().toString());

        Date orderDate = model.getOrderDate();
        if (orderDate == null) {
            textOrderDate.setText(null);
        } else {
            textOrderDate.setText(format(orderDate, DATE_PATTERN));
        }

        textCreditCardNumber.setText(model.getCreditCardNumber());
        textCreditCardType.setText(model.getCreditCardType());
        textCreditCardExpiryDate.setText(model.getCreditCardExpiryDate());
        textTotal.setText(model.getTotal() == null ? null : model.getTotal()
                .toString());

        if (customerPane.getModel() != model.getCustomerModel()) {
            customerPane.setModel(model.getCustomerModel());
        }

        if (deliveryAddressPane.getModel() != model.getDeliveryAddressModel()) {
            deliveryAddressPane.setModel(model.getDeliveryAddressModel());
        }

        ((OrderLineTableModel) tableOrderLines.getYapsModel())
                .setOrderLines(model.getOrderLines());
    }

    @Override
    protected OrderListener createDefaultPropertyChangeHandler() {
        return new PropertyChangeHandler();
    }


    private class PropertyChangeHandler extends OrderAdapter {

        @Override
        public void identifierChanged(XSEvent<OrderEventPropertyName, Long> evt) {
            Long oldValue = null;

            try {
                oldValue = new Long(textId.getText());
            } catch (Exception exc) {
            }

            Long newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                textId.setText(newValue == null ? null : newValue.toString());
            }
        }

        @Override
        public void orderDateChanged(XSEvent<OrderEventPropertyName, Date> evt) {
            Date oldValue = null;

            try {
                oldValue = parseDate(textOrderDate.getText(), DATE_PATTERNS);
            } catch (ParseException exc) {
                return;
            }

            Date newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                if (newValue == null) {
                    textOrderDate.setText(null);
                } else {
                    textOrderDate.setText(format(newValue, DATE_PATTERN));
                }
            }
        }

        @Override
        public void creditCardNumberChanged(
                XSEvent<OrderEventPropertyName, String> evt) {
            String oldValue = textCreditCardNumber.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                textCreditCardNumber.setText(newValue);
            }
        }

        @Override
        public void creditCardTypeChanged(
                XSEvent<OrderEventPropertyName, String> evt) {
            String oldValue = textCreditCardType.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                textCreditCardType.setText(newValue);
            }
        }

        @Override
        public void creditCardExpiryDateChanged(
                XSEvent<OrderEventPropertyName, String> evt) {
            String oldValue = textCreditCardExpiryDate.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                textCreditCardExpiryDate.setText(newValue);
            }
        }

        @Override
        public void orderLinesChanged(
                XSEvent<OrderEventPropertyName, List<OrderLine>> evt) {
            OrderLineTableModel tableModel = (OrderLineTableModel) tableOrderLines
                    .getModel();

            List<OrderLine> oldValue = tableModel.getOrderlines();
            List<OrderLine> newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                tableModel.setOrderLines(newValue);
            }
        }

        @Override
        public void totalChanged(XSEvent<OrderEventPropertyName, Float> evt) {
            Float oldValue = null;

            try {
                oldValue = new Float(textTotal.getText());
            } catch (Exception exc) {
            }

            Float newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                textTotal
                        .setText(newValue == null ? null : newValue.toString());
            }
        }

    }


    @Override
    protected void synchronizeViewType(YapsViewType viewType) {
        textId.setEditable(viewType == FIND || viewType == CREATE
                || viewType == FIND_OR_CREATE);
        textOrderDate.setEditable(viewType != READ && viewType != DELETE);
        textCreditCardNumber
                .setEditable(viewType != READ && viewType != DELETE);
        textCreditCardType.setEditable(viewType != READ && viewType != DELETE);
        textCreditCardExpiryDate.setEditable(viewType != READ
                && viewType != DELETE);
        textTotal.setEditable(viewType != READ && viewType != DELETE);

        customerPane.setViewType(viewType);
        deliveryAddressPane.setViewType(viewType);
    }

    @Override
    public String toString() {
        String text = "Order";

        if (model.getIdentifier() != null && !model.getIdentifier().equals("")) {
            text += " - " + model.getIdentifier();
        }

        return text;
    }

}