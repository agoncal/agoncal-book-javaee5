package org.agoncal.book.javaee5.petstore.client.ui.catalog.category.model;


import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.event.CategoryEventPropertyName;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.event.CategoryListener;
import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.vstm.fwk.client.ui.xswing.core.model.XSModel;


public interface CategoryModel extends
        XSModel<CategoryListener, CategoryEventPropertyName> {

    public Category getCategory();

    public void setCategory(Category category);


    public Long getIdentifierToFind();

    public void setIdentifierToFind(Long identifier);

    public Long getIdentifier();


    public String getName();

    public void setName(String name);


    public String getDescription();

    public void setDescription(String description);

}