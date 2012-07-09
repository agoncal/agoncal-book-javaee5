package org.agoncal.book.javaee5.petstore.jsf;

import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.agoncal.book.javaee5.petstore.entity.catalog.Item;
import org.agoncal.book.javaee5.petstore.entity.catalog.Product;
import org.agoncal.book.javaee5.petstore.stateless.catalog.CatalogLocal;

import javax.ejb.EJB;
import java.util.List;

/**
 * @author Antonio Goncalves
 */
public class CatalogController extends Controller {

    // ======================================
    // =             Attributes             =
    // ======================================
    @EJB
    private CatalogLocal catalogBean;

    private final String cname = this.getClass().getName();

    private String keyword;
    private Category category;
    private Product product;
    private Item item;
    private List<Product> products;
    private List<Item> items;

    // ======================================
    // =             Constants              =
    // ======================================

    // ======================================
    // =            Constructors            =
    // ======================================

    // ======================================
    // =          Business methods          =
    // ======================================

    public String doFindProducts() {
        final String mname = "doFindProducts";
        logger.entering(cname, mname);

        String navigateTo = null;

        try {
            category = catalogBean.findCategory(getParamId("categoryId"));
            products = category.getProducts();
            navigateTo = "products.displayed";
        } catch (Exception e) {
            addMessage(cname, mname, e);
        }

        logger.exiting(cname, mname, navigateTo);
        return navigateTo;
    }

    public String doFindItems() {
        final String mname = "doFindItems";
        logger.entering(cname, mname);

        String navigateTo = null;

        try {
            product = catalogBean.findProduct(getParamId("productId"));
            items = product.getItems();
            navigateTo = "items.displayed";
        } catch (Exception e) {
            addMessage(cname, mname, e);
        }

        logger.exiting(cname, mname, navigateTo);
        return navigateTo;
    }

    public String doFindItem() {
        final String mname = "doFindItem";
        logger.entering(cname, mname);

        String navigateTo = null;

        try {
            item = catalogBean.findItem(getParamId("itemId"));
            navigateTo = "item.displayed";
        } catch (Exception e) {
            addMessage(cname, mname, e);
        }

        logger.exiting(cname, mname, navigateTo);
        return navigateTo;
    }

    public String doSearch() {
        final String mname = "doSearch";
        logger.entering(cname, mname);

        String navigateTo = null;

        try {
            items = catalogBean.searchItems(keyword);
            navigateTo = "items.found";
        } catch (Exception e) {
            addMessage(cname, mname, e);
        }

        logger.exiting(cname, mname, navigateTo);
        return navigateTo;
    }

    // ======================================
    // =          Protected methods         =
    // ======================================

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    public List<Product> getProducts() {
        return products;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    // ======================================
    // =         hash, equals, toString     =
    // ======================================
}