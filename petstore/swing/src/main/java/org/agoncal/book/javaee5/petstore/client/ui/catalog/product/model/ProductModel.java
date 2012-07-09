package org.agoncal.book.javaee5.petstore.client.ui.catalog.product.model;


import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.event.ProductEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.event.ProductListener;
import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.agoncal.book.javaee5.petstore.entity.catalog.Product;
import org.vstm.fwk.client.ui.xswing.core.model.XSModel;


public interface ProductModel extends
        XSModel<ProductListener, ProductEventPropertyName> {

    public Product getProduct();

    public void setProduct(Product product);


    public Long getIdentifierToFind();

    public void setIdentifierToFind(Long identifier);

    public Long getIdentifier();


    public String getName();

    public void setName(String name);


    public String getDescription();

    public void setDescription(String description);


    public Category getCategory();

    public void setCategory(Category category);

}