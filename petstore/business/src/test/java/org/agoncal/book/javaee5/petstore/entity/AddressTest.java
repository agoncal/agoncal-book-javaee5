package org.agoncal.book.javaee5.petstore.entity;

import org.agoncal.book.javaee5.petstore.exception.ValidationException;
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
 * @see Address
 */
public class AddressTest {

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
        Address address = new Address();

        // Gets all the objects from the database
        int firstFindAll = findAll();

        // Creates an object and persists it into the database
        address = getMockAddressValues(address, random);
        tx.begin();
        em.persist(address);
        tx.commit();
        Long id = address.getId();

        // Check that the object has been created with the right values
        address = em.find(Address.class, id);
        assertNotNull("Object should exist", address);
        checkMockAddressValues(address, random);

        // Updates the object with new values
        address = getMockAddressValues(address, updateRandom);
        tx.begin();
        em.merge(address);
        tx.commit();

        // Checks the object has been updated with the new values
        address = em.find(Address.class, id);
        assertNotNull("Object should exist", address);
        checkMockAddressValues(address, updateRandom);

        // Gets all the objects from the database...
        int secondFindAll = findAll();

        // ...checks there is an extra object in the database
        if (firstFindAll + 1 != secondFindAll) fail("The collection size should have increased by 1");

        // Deletes the object from the database
        tx.begin();
        em.remove(address);
        tx.commit();

        // Checks the object has been deleted
        address = em.find(Address.class, id);
        assertNull("Object should not exist", address);

        // Gets all the objects from the database...
        int thirdFindAll = findAll();

        // ... checks there is the initial number of objects in the database
        if (firstFindAll != thirdFindAll) fail("The collection size should have be the same as original");
    }

    @Test
    public void invalidValues() throws Exception {
        Address address;

        // Tries to create an object with empty values
        try {
            address = new Address("", "", "", "");
            tx.begin();
            em.persist(address);
            fail("Object with empty values should not be created");
        } catch (ValidationException e) {
            tx.rollback();
        }

        // Tries to create an object with null values
        try {
            address = new Address(null, null, null, null);
            tx.begin();
            em.persist(address);
            fail("Object with null values should not be created");
        } catch (ValidationException e) {
            tx.rollback();
        }
    }

    // ======================================
    // =           Private Methods          =
    // ======================================

    private int findAll() {
        Query query = em.createQuery("SELECT a FROM Address a");
        List result = query.getResultList();
        if (result == null)
            return 0;
        else
            return result.size();
    }
}
