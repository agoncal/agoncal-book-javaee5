package org.agoncal.book.javaee5.petstore.entity.catalog;

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
 * @see Category
 */
public class CategoryTest {

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
        Category category = new Category();

        // Gets all the objects from the database
        int firstFindAll = findAll();

        // Creates an object and persists it into the database
        category = getMockCategoryValues(category, random);
        tx.begin();
        em.persist(category);
        tx.commit();
        Long id = category.getId();

        // Check that the object has been created with the right values
        category = em.find(Category.class, id);
        assertNotNull("Object should exist", category);
        checkMockCategoryValues(category, random);

        // Updates the object with new values
        category = getMockCategoryValues(category, updateRandom);
        tx.begin();
        em.merge(category);
        tx.commit();

        // Checks the object has been updated with the new values
        category = em.find(Category.class, id);
        assertNotNull("Object should exist", category);
        checkMockCategoryValues(category, updateRandom);

        // Gets all the objects from the database...
        int secondFindAll = findAll();

        // ...checks there is an extra object in the database
        if (firstFindAll + 1 != secondFindAll) fail("The collection size should have increased by 1");

        // Deletes the object from the database
        tx.begin();
        em.remove(category);
        tx.commit();

        // Checks the object has been deleted
        category = em.find(Category.class, id);
        assertNull("Object should not exist", category);

        // Gets all the objects from the database...
        int thirdFindAll = findAll();

        // ... checks there is the initial number of objects in the database
        if (firstFindAll != thirdFindAll) fail("The collection size should have be the same as original");
    }

    /**
     * Ce test s'assure que la cr�ation et la modification prennent en compte les
     * valeurs obligatoires.
     */
    @Test
    public void invalidValues() throws Exception {
        Category category = null;

        // Tries to create an object with empty values
        try {
            category = new Category("", "");
            tx.begin();
            em.persist(category);
            fail("Object with empty values should not be created");
        } catch (ValidationException e) {
            tx.rollback();
        }

        // Tries to create an object with null values
        try {
            category = new Category(null, null);
            tx.begin();
            em.persist(category);
            fail("Object with null values should not be created");
        } catch (ValidationException e) {
            tx.rollback();
        }

        // Creates an object and persists it into the database
        category = getMockCategoryValues(category, getRandom());
        tx.begin();
        em.persist(category);
        tx.commit();
        Long id = category.getId();

        // Essaie de modifier l'objet avec des valeurs vides
        try {
            category.setName("");
            category.setDescription("");
            tx.begin();
            em.merge(category);
            tx.commit();
            fail("Object with empty values should not be updated");
        } catch (RollbackException e) {
        }

        // Essaie de modifier l'objet avec des valeurs null
        try {
            category.setName(null);
            category.setDescription(null);
            tx.begin();
            em.merge(category);
            tx.commit();
            fail("Object with null values should not be updated");
        } catch (RollbackException e) {
        }

        // Deletes the object from the database
        tx.begin();
        em.remove(em.find(Category.class, id));
        tx.commit();
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================
    private int findAll() {
        Query query = em.createQuery("SELECT a FROM Category a");
        List result = query.getResultList();
        if (result == null)
            return 0;
        else
            return result.size();
    }
}
