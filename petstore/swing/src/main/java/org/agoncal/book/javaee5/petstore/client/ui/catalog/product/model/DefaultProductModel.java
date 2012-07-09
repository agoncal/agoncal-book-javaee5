package org.agoncal.book.javaee5.petstore.client.ui.catalog.product.model;


import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.agoncal.book.javaee5.petstore.entity.catalog.Product;

import static org.agoncal.book.javaee5.petstore.client.ui.catalog.product.event.ProductEventPropertyName.*;


public class DefaultProductModel extends AbstractProductModel {

    private static final long serialVersionUID = 270830383994219430L;


    private Product product;
    private Long identifierToFind;


    public DefaultProductModel() {
        setProduct(new Product());
    }

    public DefaultProductModel(Product product) {
        setProduct(product);
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("product must be non null");
        }

        Product oldValue = this.product;
        Product newValue = product;

        this.product = product;

        if (oldValue != null) {
            fireXSChanged(this, IDENTIFIER_CHANGED, oldValue.getId(), newValue
                    .getId());
            fireXSChanged(this, NAME_CHANGED, oldValue.getName(), newValue
                    .getName());
            fireXSChanged(this, DESCRIPTION_CHANGED, oldValue.getDescription(),
                    newValue.getDescription());
            fireXSChanged(this, CATEGORY_CHANGED, oldValue.getCategory(),
                    newValue.getCategory());
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
        return product.getId();
    }


    public String getName() {
        return product.getName();
    }

    public void setName(String name) {
        Object oldValue = product.getName();
        Object newValue = name;

        product.setName(name);

        fireXSChanged(this, NAME_CHANGED, oldValue, newValue);
    }

    public String getDescription() {
        return product.getDescription();
    }

    public void setDescription(String description) {
        Object oldValue = product.getDescription();
        Object newValue = description;

        product.setDescription(description);

        fireXSChanged(this, DESCRIPTION_CHANGED, oldValue, newValue);
    }

    public Category getCategory() {
        return product.getCategory();
    }

    public void setCategory(Category category) {
        Object oldValue = product.getCategory();
        Object newValue = category;

        product.setCategory(category);

        fireXSChanged(this, CATEGORY_CHANGED, oldValue, newValue);
    }


    public void reset() {
        setName(null);
        setDescription(null);
        setCategory(null);
    }

}