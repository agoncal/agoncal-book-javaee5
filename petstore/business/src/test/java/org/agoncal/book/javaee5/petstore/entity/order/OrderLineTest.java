package org.agoncal.book.javaee5.petstore.entity.order;

import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.agoncal.book.javaee5.petstore.entity.catalog.Item;
import org.agoncal.book.javaee5.petstore.entity.catalog.Product;
import org.agoncal.book.javaee5.petstore.util.Constants;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

import static org.agoncal.book.javaee5.petstore.TestHelper.*;
import static org.junit.Assert.*;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 * @see org.agoncal.book.javaee5.petstore.entity.Address
 */
public class OrderLineTest {

    // ======================================
    // =             Attributes             =
    // ======================================

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;

    // ======================================
    // =              Fixture               =
    // ======================================

    @BeforeClass
    public static void initEntityManager() {
        emf = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void closeEntityManager() {
        em.close();
        emf.close();
    }

    @Before
    public void initEntityTransaction() {
        tx = em.getTransaction();
    }

    // ======================================
    // =              Test cases            =
    // ======================================

    @Test
    public void crud() throws Exception {
        Long random = getRandom();
        Long updateRandom = getRandom();
        OrderLine orderLine = new OrderLine();
        Item item = new Item();
        Product product = new Product();
        Category category = new Category();

        // Gets all the objects from the database
        int firstFindAll = findAll();

        // Creates an object and persists it into the database
        category = getMockCategoryValues(category, random);
        product = getMockProductValues(product, random);
        product.setCategory(category);
        item = getMockItemValues(item, random);
        item.setProduct(product);

        orderLine = getMockOrderLineValues(orderLine, random);
        orderLine.setItem(item);

        tx.begin();
        em.persist(category);
        em.persist(product);
        em.persist(item);
        em.persist(orderLine);
        tx.commit();
        Long id = orderLine.getId();

        // Check that the object has been created with the right values
        orderLine = em.find(OrderLine.class, id);
        assertNotNull("Object should exist", orderLine);
        checkMockOrderLineValues(orderLine, random);

        // Updates the object with new values
        orderLine = getMockOrderLineValues(orderLine, updateRandom);
        tx.begin();
        em.merge(orderLine);
        tx.commit();

        // Checks the object has been updated with the new values
        orderLine = em.find(OrderLine.class, id);
        assertNotNull("Object should exist", orderLine);
        checkMockOrderLineValues(orderLine, updateRandom);

        // Gets all the objects from the database...
        int secondFindAll = findAll();

        // ...checks there is an extra object in the database
        if (firstFindAll + 1 != secondFindAll) fail("The collection size should have increased by 1");

        // Deletes the object from the database
        item = orderLine.getItem();
        tx.begin();
        em.remove(orderLine);
        em.remove(item);
        em.remove(product);
        em.remove(category);
        tx.commit();

        // Checks the object has been deleted
        orderLine = em.find(OrderLine.class, id);
        assertNull("Object should not exist", orderLine);

        // Gets all the objects from the database...
        int thirdFindAll = findAll();

        // ... checks there is the initial number of objects in the database
        if (firstFindAll != thirdFindAll) fail("The collection size should have be the same as original");
    }

    // ======================================
    // =           Private Methods          =
    // ======================================

    private int findAll() {
        Query query = em.createQuery("SELECT o FROM OrderLine o");
        List result = query.getResultList();
        if (result == null)
            return 0;
        else
            return result.size();
    }
}