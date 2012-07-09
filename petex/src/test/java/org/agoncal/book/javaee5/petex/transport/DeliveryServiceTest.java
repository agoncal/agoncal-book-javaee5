package org.agoncal.book.javaee5.petex.transport;

import org.junit.Test;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 */
public class DeliveryServiceTest {

    // ======================================
    // =              Test cases            =
    // ======================================

    /**
     * This test ensures that the delivery method works
     */

    @Test
    public void shouldCheckDeliveryWorksWithNoException() throws Exception {
        Delivery delivery = new Delivery();

        DeliveryPlace from = new DeliveryPlace("Yaps", "57 Ritherdon Rd", "San Francisco", "LA", "4578", "USA");
        DeliveryPlace to = new DeliveryPlace("Mr Smith", "452 White Square", "New York", "NY", "84587", "USA");

        delivery.deliverItems(from, to, "Order 123456");
    }
}