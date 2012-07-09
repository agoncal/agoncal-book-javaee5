package org.agoncal.book.javaee5.petstore.client.ui.catalog.item;


import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.event.ItemAdapter;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.event.ItemEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.event.ItemListener;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.model.DefaultItemModel;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.model.ItemModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsComponentPane;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;
import org.agoncal.book.javaee5.petstore.client.ui.util.combo.ProductComboItem;
import org.agoncal.book.javaee5.petstore.delegate.CatalogDelegate;
import org.agoncal.book.javaee5.petstore.entity.catalog.Product;
import org.apache.commons.lang.ObjectUtils;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.*;


public class ItemPane extends
        YapsComponentPane<ItemModel, ItemListener, ItemEventPropertyName> {

    private static final long serialVersionUID = 5074118210045914459L;


    private JTextField idField;
    private JTextField nameField;
    private JTextField unitCostField;
    private JTextField imagePathField;
    private JComboBox productCombo;


    public ItemPane() {
        super();
    }

    public ItemPane(ItemModel model) {
        super(model);
    }

    public ItemPane(YapsViewType viewType) {
        super(viewType);
    }

    public ItemPane(ItemModel model, YapsViewType viewType) {
        super(model, viewType);
    }


    @Override
    protected ItemModel createDefaultModel() {
        return new DefaultItemModel();
    }

    @Override
    protected void initView() {
        idField = new JTextField();
        nameField = new JTextField();
        unitCostField = new JTextField();
        imagePathField = new JTextField();
        productCombo = new JComboBox();


        try {
            List<Product> products = CatalogDelegate.findProducts();

            productCombo.addItem(null);
            for (Product product : products) {
                productCombo.addItem(new ProductComboItem(product));
            }
        } catch (Exception exc) {
            ProductComboItem error = new ProductComboItem(null);
            productCombo.addItem(error);
        }


        setLayout(new GridBagLayout());
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        int row = 0;
        Insets insets = new Insets(2, 5, 2, 5);

        add(new JLabel("Identifier"), new GridBagConstraints(0, row, 1, 1, 0.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                insets, 0, 0));
        add(idField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(new JLabel("Name"), new GridBagConstraints(0, row, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets,
                0, 0));
        add(nameField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(new JLabel("Unit Cost"), new GridBagConstraints(0, row, 1, 1, 0.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                insets, 0, 0));
        add(unitCostField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(new JLabel("Image Path"), new GridBagConstraints(0, row, 1, 1, 0.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                insets, 0, 0));
        add(imagePathField, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets,
                0, 0));

        add(new JLabel("Product"), new GridBagConstraints(0, row, 1, 1, 0.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                insets, 0, 0));
        add(productCombo, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
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

        nameField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                String oldValue = model.getName();
                String newValue = nameField.getText();

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setName(newValue);
                }
            }

        });

        unitCostField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                Float oldValue = model.getUnitCost();
                Float newValue = null;

                try {
                    newValue = new Float(unitCostField.getText());
                    unitCostField.setForeground(Color.black);
                } catch (NumberFormatException exc) {
                    newValue = oldValue;
                    unitCostField.setForeground(Color.red);
                }

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setUnitCost(newValue);
                }
            }

        });

        imagePathField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                String oldValue = model.getImagePath();
                String newValue = imagePathField.getText();

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setImagePath(newValue);
                }
            }

        });

        productCombo.addItemListener(new java.awt.event.ItemListener() {

            public void itemStateChanged(ItemEvent evt) {
                Product oldValue = model.getProduct();
                Product newValue = ((ProductComboItem) evt.getItem())
                        .getProduct();

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setProduct(newValue);
                }
            }

        });

    }

    @Override
    protected void initViewValues() {
        idField.setText(model.getIdentifier() == null ? null : model
                .getIdentifier().toString());
        nameField.setText(model.getName());
        unitCostField.setText(model.getUnitCost() == null ? null : model
                .getUnitCost().toString());
        imagePathField.setText(model.getImagePath());
        productCombo.setSelectedItem(new ProductComboItem(model.getProduct()));
    }

    @Override
    protected ItemListener createDefaultPropertyChangeHandler() {
        return new PropertyChangeHandler();
    }


    private class PropertyChangeHandler extends ItemAdapter {

        @Override
        public void identifierChanged(XSEvent<ItemEventPropertyName, Long> evt) {
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
        public void nameChanged(XSEvent<ItemEventPropertyName, String> evt) {
            String oldValue = nameField.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                nameField.setText(newValue);
            }
        }

        @Override
        public void unitCostChanged(XSEvent<ItemEventPropertyName, Float> evt) {
            Float oldValue = null;

            try {
                oldValue = new Float(unitCostField.getText());
            } catch (Exception exc) {
            }

            Float newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                unitCostField.setText(newValue == null ? null : newValue
                        .toString());
            }
        }

        @Override
        public void imagePathChanged(XSEvent<ItemEventPropertyName, String> evt) {
            String oldValue = imagePathField.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                imagePathField.setText(newValue);
            }
        }

        @Override
        public void productChanged(XSEvent<ItemEventPropertyName, Product> evt) {
            Product oldValue = null;
            Object selectedItem = productCombo.getSelectedItem();

            if (selectedItem != null
                    && selectedItem instanceof ProductComboItem) {
                oldValue = ((ProductComboItem) selectedItem).getProduct();
            }

            Product newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                productCombo.setSelectedItem(new ProductComboItem(newValue));
            }
        }

    }


    @Override
    protected void synchronizeViewType(YapsViewType viewType) {
        idField.setEditable(viewType == FIND || viewType == CREATE
                || viewType == FIND_OR_CREATE);
        nameField.setEditable(viewType != READ && viewType != DELETE);
        unitCostField.setEditable(viewType != READ && viewType != DELETE);
        imagePathField.setEditable(viewType != READ && viewType != DELETE);
        productCombo.setEnabled(viewType != READ && viewType != DELETE);
    }

    @Override
    public String toString() {
        String text = "Item";

        if (model.getIdentifier() != null && !model.getIdentifier().equals("")) {
            text += " - " + model.getIdentifier();
        }

        return text;
    }

}