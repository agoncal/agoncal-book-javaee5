package org.agoncal.book.javaee5.petstore.client.ui.catalog.item.model;


import org.agoncal.book.javaee5.petstore.entity.catalog.Item;
import org.agoncal.book.javaee5.petstore.entity.catalog.Product;

import static org.agoncal.book.javaee5.petstore.client.ui.catalog.item.event.ItemEventPropertyName.*;


public class DefaultItemModel extends AbstractItemModel {

    private static final long serialVersionUID = -3870151937579538411L;


    private Item item;
    private Long identifierToFind;


    public DefaultItemModel() {
        setItem(new Item());
    }

    public DefaultItemModel(Item item) {
        setItem(item);
    }


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be non null");
        }

        Item oldValue = this.item;
        Item newValue = item;

        this.item = item;

        if (oldValue != null) {
            fireXSChanged(this, IDENTIFIER_CHANGED, oldValue.getId(), newValue
                    .getId());
            fireXSChanged(this, NAME_CHANGED, oldValue.getName(), newValue
                    .getName());
            fireXSChanged(this, UNIT_COST_CHANGED, oldValue.getUnitCost(),
                    newValue.getUnitCost());
            fireXSChanged(this, IMAGE_PATH_CHANGED, oldValue.getImagePath(),
                    newValue.getImagePath());
            fireXSChanged(this, PRODUCT_CHANGED, oldValue.getProduct(),
                    newValue.getProduct());
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
        return item.getId();
    }


    public String getName() {
        return item.getName();
    }

    public void setName(String name) {
        Object oldValue = item.getName();
        Object newValue = name;

        item.setName(name);

        fireXSChanged(this, NAME_CHANGED, oldValue, newValue);
    }

    public Float getUnitCost() {
        return item.getUnitCost();
    }

    public void setUnitCost(Float unitCost) {
        Object oldValue = item.getUnitCost();
        Object newValue = unitCost;

        item.setUnitCost(unitCost);

        fireXSChanged(this, UNIT_COST_CHANGED, oldValue, newValue);
    }

    public String getImagePath() {
        return item.getImagePath();
    }

    public void setImagePath(String imagePath) {
        Object oldValue = item.getImagePath();
        Object newValue = imagePath;

        item.setImagePath(imagePath);

        fireXSChanged(this, IMAGE_PATH_CHANGED, oldValue, newValue);
    }

    public Product getProduct() {
        return item.getProduct();
    }

    public void setProduct(Product product) {
        Object oldValue = item.getProduct();
        Object newValue = product;

        item.setProduct(product);

        fireXSChanged(this, PRODUCT_CHANGED, oldValue, newValue);
    }


    public void reset() {
        setName(null);
        setUnitCost(null);
        setImagePath(null);
        setProduct(null);
    }

}