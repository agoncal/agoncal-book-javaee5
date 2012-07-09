package org.agoncal.book.javaee5.petstore.stateless.catalog;

import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.agoncal.book.javaee5.petstore.entity.catalog.Item;
import org.agoncal.book.javaee5.petstore.entity.catalog.Product;
import org.agoncal.book.javaee5.petstore.exception.ValidationException;
import org.agoncal.book.javaee5.petstore.util.Constants;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 *         This class is a facade for all catalog services.
 */
@SuppressWarnings(value = "unchecked")
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@Stateless(name = "CatalogSB", mappedName = "ejb/stateless/Catalog")
public class CatalogBean implements CatalogRemote, CatalogLocal {

    // ======================================
    // =             Attributes             =
    // ======================================

    @PersistenceContext(unitName = "bookJavaEE5PU")
    private EntityManager em;

    private final String cname = this.getClass().getName();
    private Logger logger = Logger.getLogger(Constants.LOGGER_STATELESS);

    // ======================================
    // =    Business Methods for Category   =
    // ======================================

    public Category createCategory(final Category category) {
        final String mname = "getNewCategory";
        logger.entering(cname, mname, category);

        if (category == null)
            throw new ValidationException("Category object is null");

        em.persist(category);

        logger.exiting(cname, mname, category);
        return category;
    }

    public Category findCategory(final Long categoryId) {
        final String mname = "findCategory";
        logger.entering(cname, mname, categoryId);

        if (categoryId == null)
            throw new ValidationException("Invalid id");

        Category category;

        category = em.find(Category.class, categoryId);

        logger.exiting(cname, mname, category);
        return category;
    }

    public void deleteCategory(final Category category) {
        final String mname = "deleteCategory";
        logger.entering(cname, mname, category);

        if (category == null)
            throw new ValidationException("Category object is null");

        em.remove(em.merge(category));

        logger.exiting(cname, mname);
    }

    public Category updateCategory(final Category category) {
        final String mname = "updateCategory";
        logger.entering(cname, mname, category);

        if (category == null)
            throw new ValidationException("Category object is null");

        em.merge(category);

        logger.exiting(cname, mname, category);
        return category;
    }

    public List<Category> findCategories() {
        final String mname = "findCategories";
        logger.entering(cname, mname);

        Query query;
        List<Category> categories;

        query = em.createQuery("SELECT c FROM Category c ORDER BY c.name");
        categories = query.getResultList();

        logger.exiting(cname, mname, categories.size());
        return categories;
    }

    // ======================================
    // =    Business Methods for Product    =
    // ======================================
    public Product createProduct(final Product product, final Category category) {
        final String mname = "createProduct";
        logger.entering(cname, mname, product);

        if (product == null)
            throw new ValidationException("Product object is null");
        if (category == null)
            throw new ValidationException("Product must be attached to a category");

        product.setCategory(category);

        em.persist(product);

        logger.exiting(cname, mname, product);
        return product;
    }

    public Product findProduct(final Long productId) {
        final String mname = "findProduct";
        logger.entering(cname, mname, productId);

        if (productId == null)
            throw new ValidationException("Invalid id");

        Product product;

        product = em.find(Product.class, productId);

        logger.exiting(cname, mname, product);
        return product;
    }

    public void deleteProduct(final Product product) {
        final String mname = "deleteProduct";
        logger.entering(cname, mname, product);

        if (product == null)
            throw new ValidationException("Product object is null");

        em.remove(em.merge(product));

        logger.exiting(cname, mname);
    }

    public Product updateProduct(final Product product, final Category category) {
        final String mname = "updateProduct";
        logger.entering(cname, mname, product);

        if (product == null)
            throw new ValidationException("Product object is null");
        if (category == null)
            throw new ValidationException("Product must be attached to a category");

        product.setCategory(category);

        em.merge(product);

        logger.exiting(cname, mname, product);
        return product;
    }

    public List<Product> findProducts() {
        final String mname = "findCategories";
        logger.entering(cname, mname);

        Query query;
        List<Product> products;

        query = em.createQuery("SELECT p FROM Product p ORDER BY p.name");
        products = query.getResultList();

        logger.exiting(cname, mname, products.size());
        return products;
    }

    // ======================================
    // =      Business Methods for Item     =
    // ======================================

    public Item createItem(final Item item, final Product product) {
        final String mname = "createItem";
        logger.entering(cname, mname, item);

        if (item == null)
            throw new ValidationException("Item object is null");
        if (product == null)
            throw new ValidationException("Item must be attached to a product");

        item.setProduct(product);

        em.persist(item);

        logger.exiting(cname, mname, item);
        return item;
    }

    public Item findItem(final Long itemId) {
        final String mname = "findItem";
        logger.entering(cname, mname, itemId);

        if (itemId == null)
            throw new ValidationException("Invalid id");

        Item item;

        item = em.find(Item.class, itemId);

        logger.exiting(cname, mname, item);
        return item;
    }

    public void deleteItem(final Item item) {
        final String mname = "deleteItem";
        logger.entering(cname, mname, item);

        if (item == null)
            throw new ValidationException("Item object is null");

        em.remove(em.merge(item));

        logger.exiting(cname, mname);
    }

    public Item updateItem(final Item item, final Product product) {
        final String mname = "updateItem";
        logger.entering(cname, mname, item);

        if (item == null)
            throw new ValidationException("Item object is null");
        if (product == null)
            throw new ValidationException("Item must be attached to a product");

        item.setProduct(product);

        em.merge(item);

        logger.exiting(cname, mname, item);
        return item;
    }

    public List<Item> findItems() {
        final String mname = "findCategories";
        logger.entering(cname, mname);

        Query query;
        List<Item> items;

        query = em.createQuery("SELECT i FROM Item i ORDER BY i.name");
        items = query.getResultList();

        logger.exiting(cname, mname, items.size());
        return items;
    }


    public List<Item> searchItems(final String keyword) {
        final String mname = "searchItems";
        logger.entering(cname, mname, keyword);

        Query query;
        List<Item> items;

        query = em.createQuery("SELECT i FROM Item i WHERE UPPER(i.name) LIKE :keyword OR UPPER(i.product.name) LIKE :keyword ORDER BY i.product.category.name, i.product.name");
        query.setParameter("keyword", "%" + keyword.toUpperCase() + "%");
        items = query.getResultList();

        logger.exiting(cname, mname, items.size());
        return items;
    }
}
