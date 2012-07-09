package org.agoncal.book.javaee5.petstore.stateless.order;

import org.agoncal.book.javaee5.petstore.entity.order.Order;

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
public interface OrderRemote {

    // ======================================
    // =             Constants              =
    // ======================================

    Order findOrder(Long orderId);

    void deleteOrder(Order order);

    List<Order> findOrders();
}