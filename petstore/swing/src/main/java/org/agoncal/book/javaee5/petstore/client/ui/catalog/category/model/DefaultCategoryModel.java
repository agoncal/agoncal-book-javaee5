package org.agoncal.book.javaee5.petstore.client.ui.catalog.category.model;


import org.agoncal.book.javaee5.petstore.entity.catalog.Category;

import static org.agoncal.book.javaee5.petstore.client.ui.catalog.category.event.CategoryEventPropertyName.*;


public class DefaultCategoryModel extends AbstractCategoryModel {

    private static final long serialVersionUID = 1602655452741754011L;


    private Category category;
    private Long identifierToFind;


    public DefaultCategoryModel() {
        setCategory(new Category());
    }

    public DefaultCategoryModel(Category category) {
        setCategory(category);
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("category must be non null");
        }

        Category oldValue = this.category;
        Category newValue = category;

        this.category = category;

        if (oldValue != null) {
            fireXSChanged(this, IDENTIFIER_CHANGED, oldValue.getId(), newValue
                    .getId());
            fireXSChanged(this, NAME_CHANGED, oldValue.getName(), newValue
                    .getName());
            fireXSChanged(this, DESCRIPTION_CHANGED, oldValue.getDescription(),
                    newValue.getDescription());
        }
    }


    public Long getIdentifierToFind() {
        return identifierToFind;
    }

    public void setIdentifierToFind(Long identifierToFind) {
        Object oldValue = this.identifierToFind;
        Object newValue = identifierToFind;

        this.identifierToFind = identifierToFind;

        fireXSChanged(this, IDENTIFIER_CHANGED, oldValue, newValue);
    }

    public Long getIdentifier() {
        return category.getId();
    }


    public String getName() {
        return category.getName();
    }

    public void setName(String name) {
        Object oldValue = category.getName();
        Object newValue = name;

        category.setName(name);

        fireXSChanged(this, NAME_CHANGED, oldValue, newValue);
    }

    public String getDescription() {
        return category.getDescription();
    }

    public void setDescription(String description) {
        Object oldValue = category.getDescription();
        Object newValue = description;

        category.setDescription(description);

        fireXSChanged(this, DESCRIPTION_CHANGED, oldValue, newValue);
    }


    public void reset() {
        setName(null);
        setDescription(null);
    }

}