package org.agoncal.book.javaee5.petstore.stateless.catalog;

import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.agoncal.book.javaee5.petstore.entity.catalog.Item;
import org.agoncal.book.javaee5.petstore.entity.catalog.Product;

import javax.ejb.Local;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 */
@Local
public interface CatalogLocal {

    // ======================================
    // =          Business methods          =
    // ======================================

    Category findCategory(Long categoryId);

    Product findProduct(Long productId);

    Item findItem(Long itemId);

    List<Item> searchItems(String keyword);
}