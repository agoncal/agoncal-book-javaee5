package org.agoncal.book.javaee5.petstore.stateful;

import org.agoncal.book.javaee5.petstore.dto.CartItem;
import org.agoncal.book.javaee5.petstore.entity.catalog.Item;

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
public interface ShoppingCartLocal {

    // ======================================
    // =          Business methods          =
    // ======================================

    void addItem(Item item);

    void removeItem(Item item);

    Float getTotal();

    void empty();

    List<CartItem> getCartItems();
}