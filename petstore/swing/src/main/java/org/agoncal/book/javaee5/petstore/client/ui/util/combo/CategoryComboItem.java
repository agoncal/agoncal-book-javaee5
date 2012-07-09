package org.agoncal.book.javaee5.petstore.client.ui.util.combo;


import org.agoncal.book.javaee5.petstore.entity.catalog.Category;


public class CategoryComboItem {

    private final Category category;


    public CategoryComboItem(final Category category) {
        this.category = category;
    }


    public Category getCategory() {
        return category;
    }


    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result
                + ((category == null) ? 0 : category.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CategoryComboItem other = (CategoryComboItem) obj;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return category == null ? "Loading error ..." : category.getName();
    }

}