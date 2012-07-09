package org.agoncal.book.javaee5.petstore.client.ui.catalog.category;


import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.event.CategoryAdapter;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.event.CategoryEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.event.CategoryListener;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.model.CategoryModel;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.model.DefaultCategoryModel;
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

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.*;


public class CategoryPane
        extends
        YapsComponentPane<CategoryModel, CategoryListener, CategoryEventPropertyName> {

    private static final long serialVersionUID = -3566363661706630486L;


    private JTextField idField;
    private JTextField nameField;
    private JTextArea descriptionField;


    public CategoryPane() {
        super();
    }

    public CategoryPane(CategoryModel model) {
        super(model);
    }

    public CategoryPane(YapsViewType viewType) {
        super(viewType);
    }

    public CategoryPane(CategoryModel model, YapsViewType viewType) {
        super(model, viewType);
    }


    @Override
    protected CategoryModel createDefaultModel() {
        return new DefaultCategoryModel();
    }

    @Override
    protected void initView() {
        idField = new JTextField();
        nameField = new JTextField();
        descriptionField = new JTextArea(3, 25);


        descriptionField.setWrapStyleWord(true);
        descriptionField.setLineWrap(true);
        JScrollPane scrollingDescription = new JScrollPane(descriptionField,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


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

    }

    @Override
    protected void initViewValues() {
        idField.setText(model.getIdentifier() == null ? null : model
                .getIdentifier().toString());
        nameField.setText(model.getName());
        descriptionField.setText(model.getDescription());
        descriptionField.setCaretPosition(0);
    }

    @Override
    protected CategoryListener createDefaultPropertyChangeHandler() {
        return new PropertyChangeHandler();
    }


    private class PropertyChangeHandler extends CategoryAdapter {

        @Override
        public void identifierChanged(
                XSEvent<CategoryEventPropertyName, Long> evt) {
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
        public void nameChanged(XSEvent<CategoryEventPropertyName, String> evt) {
            String oldValue = nameField.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                nameField.setText(newValue);
            }
        }

        @Override
        public void descriptionChanged(
                XSEvent<CategoryEventPropertyName, String> evt) {
            String oldValue = descriptionField.getText();
            String newValue = evt.getNewValueAsParameterizedType();

            if (!ObjectUtils.equals(oldValue, newValue)) {
                descriptionField.setText(newValue);
            }
        }

    }


    @Override
    protected void synchronizeViewType(YapsViewType viewType) {
        idField.setEditable(viewType == FIND || viewType == CREATE
                || viewType == FIND_OR_CREATE);
        nameField.setEditable(viewType != READ && viewType != DELETE);
        descriptionField.setEditable(viewType != READ && viewType != DELETE);
    }

    @Override
    public String toString() {
        String text = "Category";

        if (model.getIdentifier() != null && !model.getIdentifier().equals("")) {
            text += " - " + model.getIdentifier();
        }

        return text;
    }

}