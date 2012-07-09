package org.agoncal.book.javaee5.petstore.entity.catalog;

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
public class ItemTest {

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
        tx.begin();
        em.persist(category);
        em.persist(product);
        em.persist(item);
        tx.commit();
        Long id = item.getId();

        // Check that the object has been created with the right values
        item = em.find(Item.class, id);
        assertNotNull("Object should exist", item);
        checkMockItemValues(item, random);

        // Updates the object with new values
        item = getMockItemValues(item, updateRandom);
        tx.begin();
        em.merge(item);
        tx.commit();

        // Checks the object has been updated with the new values
        item = em.find(Item.class, id);
        assertNotNull("Object should exist", item);
        checkMockItemValues(item, updateRandom);

        // Gets all the objects from the database...
        int secondFindAll = findAll();

        // ...checks there is an extra object in the database
        if (firstFindAll + 1 != secondFindAll) fail("The collection size should have increased by 1");

        // Deletes the object from the database
        tx.begin();
        em.remove(item);
        em.remove(product);
        em.remove(category);
        tx.commit();

        // Checks the object has been deleted
        item = em.find(Item.class, id);
        assertNull("Object should not exist", item);

        // Gets all the objects from the database...
        int thirdFindAll = findAll();

        // ... checks there is the initial number of objects in the database
        if (firstFindAll != thirdFindAll) fail("The collection size should have be the same as original");
    }

    // ======================================
    // =           Private Methods          =
    // ======================================

    private int findAll() {
        Query query = em.createQuery("SELECT a FROM Item a");
        List result = query.getResultList();
        if (result == null)
            return 0;
        else
            return result.size();
    }
}
