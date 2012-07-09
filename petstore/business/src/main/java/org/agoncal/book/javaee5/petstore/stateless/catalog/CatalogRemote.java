package org.agoncal.book.javaee5.petstore.stateless.catalog;

import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.agoncal.book.javaee5.petstore.entity.catalog.Item;
import org.agoncal.book.javaee5.petstore.entity.catalog.Product;

import javax.ejb.Remote;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 */
@Remote
public interface CatalogRemote {

    // ======================================
    // =          Business methods          =
    // ======================================

    Category createCategory(Category category);

    Category findCategory(Long categoryId);

    void deleteCategory(Category category);

    Category updateCategory(Category category);

    List<Category> findCategories();

    Product createProduct(Product product, Category category);

    Product findProduct(Long productId);

    void deleteProduct(Product product);

    Product updateProduct(Product product, Category category);

    List<Product> findProducts();

    Item createItem(Item item, Product product);

    Item findItem(Long itemId);

    void deleteItem(Item item);

    Item updateItem(Item item, Product product);

    List<Item> findItems();
}