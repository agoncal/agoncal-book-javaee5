package org.agoncal.book.javaee5.petstore.entity;

import org.agoncal.book.javaee5.petstore.entity.catalog.CategoryTest;
import org.agoncal.book.javaee5.petstore.entity.catalog.ItemTest;
import org.agoncal.book.javaee5.petstore.entity.catalog.ProductTest;
import org.agoncal.book.javaee5.petstore.entity.customer.CustomerTest;
import org.agoncal.book.javaee5.petstore.entity.order.OrderLineTest;
import org.agoncal.book.javaee5.petstore.entity.order.OrderTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddressTest.class,
        CategoryTest.class,
        CustomerTest.class,
        ItemTest.class,
        OrderLineTest.class,
        OrderTest.class,
        ProductTest.class
})
public class AllDomainTests {

}