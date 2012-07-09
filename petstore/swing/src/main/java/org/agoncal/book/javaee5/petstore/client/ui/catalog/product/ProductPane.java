package org.agoncal.book.javaee5.petstore.client.ui.catalog.product;


import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.event.ProductAdapter;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.event.ProductEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.event.ProductListener;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.model.DefaultProductModel;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.model.ProductModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsComponentPane;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;
import org.agoncal.book.javaee5.petstore.client.ui.util.combo.CategoryComboItem;
import org.agoncal.book.javaee5.petstore.delegate.CatalogDelegate;
import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.apache.commons.lang.ObjectUtils;
import org.vstm.fwk.client.ui.xswing.core.event.XSEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.*;


public class ProductPane
        extends
        YapsComponentPane<ProductModel, ProductListener, ProductEventPropertyName> {

    private static final long serialVersionUID = 8616843607600688725L;


    private JTextField idField;
    private JTextField nameField;
    private JTextArea descriptionField;
    private JComboBox categoryCombo;


    public ProductPane() {
        super();
    }

    public ProductPane(ProductModel model) {
        super(model);
    }

    public ProductPane(YapsViewType viewType) {
        super(viewType);
    }

    public ProductPane(ProductModel model, YapsViewType viewType) {
        super(model, viewType);
    }


    @Override
    protected ProductModel createDefaultModel() {
        return new DefaultProductModel();
    }

    @Override
    protected void initView() {
        idField = new JTextField();
        nameField = new JTextField();
        descriptionField = new JTextArea(3, 25);
        categoryCombo = new JComboBox();


        descriptionField.setWrapStyleWord(true);
        descriptionField.setLineWrap(true);
        JScrollPane scrollingDescription = new JScrollPane(descriptionField,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        try {
            List<Category> categories = CatalogDelegate.findCategories();

            categoryCombo.addItem(null);
            for (Category category : categories) {
                categoryCombo.addItem(new CategoryComboItem(category));
            }
        } catch (Exception exc) {
            CategoryComboItem error = new CategoryComboItem(null);
            categoryCombo.addItem(error);
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

        add(new JLabel("Description"), new GridBagConstraints(0, row, 1, 1,
                0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                insets, 0, 0));
        add(scrollingDescription, new GridBagConstraints(1, row++, 1, 1, 1.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                insets, 0, 0));

        add(new JLabel("Category"), new GridBagConstraints(0, row, 1, 1, 0.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
                insets, 0, 0));
        add(categoryCombo, new GridBagConstraints(1, row++, 1, 1, 1.0, 0.0,
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

        descriptionField.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent evt) {
                String oldValue = model.getDescription();
                String newValue = descriptionField.getText();

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setDescription(newValue);
                }
            }

        });

        categoryCombo.addItemListener(new java.awt.event.ItemListener() {

            public void itemStateChanged(ItemEvent evt) {
                Category oldValue = model.getCategory();
                Category newValue = ((CategoryComboItem) evt.getItem())
                        .getCategory();

                if (!ObjectUtils.equals(oldValue, newValue)) {
                    model.setCategory(newValue);
                }
            }

        });

    }

    @Override
    protected void initViewValues() {
        idField.setText(model.getIdentifier() == null ? null : model
                .getIdentifier().toString());
        nameField.setText(model.getName());
        descriptionField.setText(model.getDescription());
        descriptionField.setCaretPosition(0);
        categoryCombo
                .setSelectedItem(new CategoryComboItem(model.getCategory()));
    }

    @Override
    protected ProductListener createDefaultPropertyChangeHandler() {
        return new PropertyChangeHandler();
    }


    private class PropertyChangeHandler extends ProductAdapter {

        @Override
        public void identifierChanged(
                XSEvent<ProductEventPropertyName, Long> evt) {
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
        public void nameChanged(XSEvent<ProductEventPropertyName, String> evt) {
            String oldValue = nameField.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                nameField.setText(newValue);
            }
        }

        @Override
        public void descriptionChanged(
                XSEvent<ProductEventPropertyName, String> evt) {
            String oldValue = descriptionField.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                descriptionField.setText(newValue);
            }
        }

        @Override
        public void categoryChanged(
                XSEvent<ProductEventPropertyName, Category> evt) {
            Category oldValue = null;
            Object selectedItem = categoryCombo.getSelectedItem();

            if (selectedItem != null
                    && selectedItem instanceof CategoryComboItem) {
                oldValue = ((CategoryComboItem) selectedItem).getCategory();
            }

            Category newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                categoryCombo.setSelectedItem(new CategoryComboItem(newValue));
            }
        }

    }


    @Override
    protected void synchronizeViewType(YapsViewType viewType) {
        idField.setEditable(viewType == FIND || viewType == CREATE
                || viewType == FIND_OR_CREATE);
        nameField.setEditable(viewType != READ && viewType != DELETE);
        descriptionField.setEditable(viewType != READ && viewType != DELETE);
        categoryCombo.setEnabled(viewType != READ && viewType != DELETE);
    }

    @Override
    public String toString() {
        String text = "Product";

        if (model.getIdentifier() != null && !model.getIdentifier().equals("")) {
            text += " - " + model.getIdentifier();
        }

        return text;
    }

}