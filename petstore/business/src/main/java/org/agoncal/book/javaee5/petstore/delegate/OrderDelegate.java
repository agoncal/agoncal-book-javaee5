package org.agoncal.book.javaee5.petstore.delegate;

import org.agoncal.book.javaee5.petstore.entity.order.Order;
import org.agoncal.book.javaee5.petstore.locator.ServiceLocator;
import org.agoncal.book.javaee5.petstore.stateless.order.OrderRemote;

import java.util.List;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 *         This class follows the Delegate design pattern. It's a one to one method
 *         with the OrderBean class. Each method delegates the call to the
 *         OrderBean class
 */
public final class OrderDelegate {

    // ======================================
    // =           Business methods         =
    // ======================================

    public static Order findOrder(Long orderId) {
        return getOrderRemote().findOrder(orderId);
    }

    public static void deleteOrder(Order order) {
        getOrderRemote().deleteOrder(order);
    }

    public static List<Order> findOrders() {
        return getOrderRemote().findOrders();
    }

    // ======================================
    // =            Private methods         =
    // ======================================

    private static OrderRemote getOrderRemote() {
        OrderRemote orderRemote;
        orderRemote = (OrderRemote) ServiceLocator.getInstance().getRemoteInterface("ejb/stateless/Order");
        return orderRemote;
    }
}
