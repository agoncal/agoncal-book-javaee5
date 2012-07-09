package org.agoncal.book.javaee5.petstore.stateless.customer;

import org.agoncal.book.javaee5.petstore.entity.Address;
import org.agoncal.book.javaee5.petstore.entity.customer.Customer;
import org.agoncal.book.javaee5.petstore.exception.ValidationException;
import org.agoncal.book.javaee5.petstore.util.Constants;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 *         This class is a facade for all customer services.
 */
@SuppressWarnings(value = "unchecked")
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@Stateless(name = "CustomerSB", mappedName = "ejb/stateless/Customer")
public class CustomerBean implements CustomerRemote, CustomerLocal {

    // ======================================
    // =             Attributes             =
    // ======================================

    @PersistenceContext(unitName = "bookJavaEE5PU")
    private EntityManager em;

    private final String cname = this.getClass().getName();
    private Logger logger = Logger.getLogger(Constants.LOGGER_STATELESS);

    // ======================================
    // =          Business methods          =
    // ======================================

    public Customer authenticate(final String login, final String password) {
        final String mname = "authenticate";
        logger.entering(cname, mname, new Object[]{login, password});

        if (login == null || "".equals(login))
            throw new ValidationException("Invalid login");

        Query query;
        Customer customer;

        // We find the customer using its login
        query = em.createQuery("SELECT c FROM Customer c WHERE c.login=:login");
        query.setParameter("login", login);
        customer = (Customer) query.getSingleResult();

        // Check if it's the right password
        if (customer != null)
            customer.matchPassword(password);

        logger.exiting(cname, mname, customer);
        return customer;
    }

    public Customer createCustomer(final Customer customer, final Address homeAddress) {
        final String mname = "createCustomer";
        logger.entering(cname, mname, customer);

        if (customer == null)
            throw new ValidationException("Customer object is null");

        customer.setHomeAddress(homeAddress);

        em.persist(customer);

        logger.exiting(cname, mname, customer);
        return customer;
    }

    public Customer findCustomer(final Long customerId) {
        final String mname = "findCustomer";
        logger.entering(cname, mname, customerId);

        if (customerId == null)
            throw new ValidationException("Invalid id");

        Customer customer;

        customer = em.find(Customer.class, customerId);

        logger.exiting(cname, mname, customer);
        return customer;
    }

    public void deleteCustomer(final Customer customer) {
        final String mname = "deleteCustomer";
        logger.entering(cname, mname, customer);

        if (customer == null)
            throw new ValidationException("Customer object is null");

        em.remove(em.merge(customer));

        logger.exiting(cname, mname);
    }

    public Customer updateCustomer(final Customer customer, final Address homeAddress) {
        final String mname = "updateCustomer";
        logger.entering(cname, mname, customer);

        if (customer == null)
            throw new ValidationException("Customer object is null");

        customer.setHomeAddress(homeAddress);

        em.merge(customer);

        logger.exiting(cname, mname, customer);
        return customer;
    }

    public List<Customer> findCustomers() {
        final String mname = "findCustomers";
        logger.entering(cname, mname);

        Query query;
        List<Customer> customers;

        query = em.createQuery("SELECT c FROM Customer c");
        customers = query.getResultList();

        logger.exiting(cname, mname, customers.size());
        return customers;
    }
}
