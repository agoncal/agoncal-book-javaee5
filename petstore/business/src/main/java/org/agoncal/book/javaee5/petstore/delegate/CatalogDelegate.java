package org.agoncal.book.javaee5.petstore.delegate;

import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.agoncal.book.javaee5.petstore.entity.catalog.Item;
import org.agoncal.book.javaee5.petstore.entity.catalog.Product;
import org.agoncal.book.javaee5.petstore.locator.ServiceLocator;
import org.agoncal.book.javaee5.petstore.stateless.catalog.CatalogRemote;

import java.util.List;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 *         This class follows the Delegate design pattern. It's a one to one method
 *         with the CatalogBean class. Each method delegates the call to the
 *         CatalogBean class
 */
public final class CatalogDelegate {

    // ======================================
    // =      Category Business methods     =
    // ======================================

    public static Category createCategory(Category category) {
        return getCatalogRemote().createCategory(category);
    }

    public static Category findCategory(Long categoryId) {
        return getCatalogRemote().findCategory(categoryId);
    }

    public static void deleteCategory(Category category) {
        getCatalogRemote().deleteCategory(category);
    }

    public static Category updateCategory(Category category) {
        return getCatalogRemote().updateCategory(category);
    }

    public static List<Category> findCategories() {
        return getCatalogRemote().findCategories();
    }

    // ======================================
    // =      Product Business methods     =
    // ======================================

    public static Product createProduct(Product product, Category category) {
        return getCatalogRemote().createProduct(product, category);
    }

    public static Product findProduct(Long productId) {
        return getCatalogRemote().findProduct(productId);
    }

    public static void deleteProduct(Product product) {
        getCatalogRemote().deleteProduct(product);
    }

    public static Product updateProduct(Product product, Category category) {
        return getCatalogRemote().updateProduct(product, category);
    }

    public static List<Product> findProducts() {
        return getCatalogRemote().findProducts();
    }

    // ======================================
    // =        Item Business methods       =
    // ======================================

    public static Item createItem(Item item, Product product) {
        return getCatalogRemote().createItem(item, product);
    }

    public static Item findItem(Long itemId) {
        return getCatalogRemote().findItem(itemId);
    }

    public static void deleteItem(Item item) {
        getCatalogRemote().deleteItem(item);
    }

    public static Item updateItem(Item item, Product product) {
        return getCatalogRemote().updateItem(item, product);
    }

    public static List<Item> findItems() {
        return getCatalogRemote().findItems();
    }

    // ======================================
    // =            Private methods         =
    // ======================================

    private static CatalogRemote getCatalogRemote() {
        CatalogRemote catalogRemote;
        catalogRemote = (CatalogRemote) ServiceLocator.getInstance().getRemoteInterface("ejb/stateless/Catalog");
        return catalogRemote;
    }
}
