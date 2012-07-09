package org.agoncal.book.javaee5.petstore.stateless.order;

import org.agoncal.book.javaee5.petstore.dto.CartItem;
import org.agoncal.book.javaee5.petstore.entity.Address;
import org.agoncal.book.javaee5.petstore.entity.customer.Customer;
import org.agoncal.book.javaee5.petstore.entity.order.CreditCard;
import org.agoncal.book.javaee5.petstore.entity.order.Order;

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
public interface OrderLocal {

    // ======================================
    // =             Constants              =
    // ======================================

    Order createOrder(Customer customer, Address deliveryAddress, CreditCard creditCard, List<CartItem> cartItems);
}