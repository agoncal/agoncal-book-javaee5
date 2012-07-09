package org.agoncal.book.javaee5.petstore.client.ui.catalog.item.model;


import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.event.ItemEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.event.ItemListener;
import org.agoncal.book.javaee5.petstore.entity.catalog.Item;
import org.agoncal.book.javaee5.petstore.entity.catalog.Product;
import org.vstm.fwk.client.ui.xswing.core.model.XSModel;


public interface ItemModel extends XSModel<ItemListener, ItemEventPropertyName> {

    public Item getItem();

    public void setItem(Item item);


    public Long getIdentifierToFind();

    public void setIdentifierToFind(Long identifier);

    public Long getIdentifier();


    public String getName();

    public void setName(String name);


    public Float getUnitCost();

    public void setUnitCost(Float unitCost);


    public String getImagePath();

    public void setImagePath(String imagePath);


    public Product getProduct();

    public void setProduct(Product product);

}