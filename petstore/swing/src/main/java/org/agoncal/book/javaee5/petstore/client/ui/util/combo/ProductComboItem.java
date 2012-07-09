package org.agoncal.book.javaee5.petstore.client.ui.util.combo;


import org.agoncal.book.javaee5.petstore.entity.catalog.Product;


public class ProductComboItem {

    private final Product product;


    public ProductComboItem(final Product product) {
        this.product = product;
    }


    public Product getProduct() {
        return product;
    }


    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((product == null) ? 0 : product.hashCode());
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
        final ProductComboItem other = (ProductComboItem) obj;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return product == null ? "Loading error ..." : product.getName();
    }

}