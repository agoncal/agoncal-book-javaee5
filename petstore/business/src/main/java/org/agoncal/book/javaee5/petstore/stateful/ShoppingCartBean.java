package org.agoncal.book.javaee5.petstore.stateful;

import org.agoncal.book.javaee5.petstore.dto.CartItem;
import org.agoncal.book.javaee5.petstore.entity.catalog.Item;
import org.agoncal.book.javaee5.petstore.util.Constants;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 */
@Stateful(name = "ShoppingCartSB")
@Local(ShoppingCartLocal.class)
public class ShoppingCartBean implements ShoppingCartLocal {

    // ======================================
    // =             Attributes             =
    // ======================================

    private List<CartItem> cartItems;

    private final String cname = this.getClass().getName();
    private Logger logger = Logger.getLogger(Constants.LOGGER_STATEFUL);

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PostConstruct
    public void initialize() {
        String mname = "initialize";
        logger.entering(cname, mname);

        cartItems = new ArrayList<CartItem>();
    }

    @PreDestroy
    public void clear() {
        String mname = "clear";
        logger.entering(cname, mname);

        cartItems = null;
    }

    // ======================================
    // =          Business methods          =
    // ======================================

    public void addItem(Item item) {
        String mname = "addItem";
        logger.entering(cname, mname, item);

        boolean itemFound = false;
        for (CartItem cartItem : cartItems) {
            // If the item already exists in the shopping cart, it updates the quantity
            if (cartItem.getItem().equals(item)) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                itemFound = true;
            }
        }
        if (!itemFound)
            // Otherwise it adds it to the shopping cart
            cartItems.add(new CartItem(item, 1));

        logger.exiting(cname, mname, cartItems.size());
    }

    public void removeItem(Item item) {
        String mname = "removeItem";
        logger.entering(cname, mname, item);

        for (CartItem cartItem : cartItems) {
            if (cartItem.getItem().equals(item)) {
                cartItems.remove(cartItem);
                return;
            }
        }

        logger.exiting(cname, mname, cartItems.size());
    }

    public Float getTotal() {
        String mname = "getTotal";
        logger.entering(cname, mname);

        if (cartItems == null || cartItems.isEmpty())
            return 0f;

        Float total = 0f;

        // sum up the quantities
        for (CartItem cartItem : cartItems) {
            total += (cartItem.getSubTotal());
        }

        logger.exiting(cname, mname, total);
        return total;
    }

    public void empty() {
        String mname = "empty";
        logger.entering(cname, mname);

        cartItems.clear();
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
