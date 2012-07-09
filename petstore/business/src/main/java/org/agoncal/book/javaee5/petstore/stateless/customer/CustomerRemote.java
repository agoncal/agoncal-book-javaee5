package org.agoncal.book.javaee5.petstore.stateless.customer;

import org.agoncal.book.javaee5.petstore.entity.Address;
import org.agoncal.book.javaee5.petstore.entity.customer.Customer;

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
public interface CustomerRemote {

    // ======================================
    // =             Constants              =
    // ======================================

    Customer createCustomer(Customer customer, Address homeAddress);

    Customer findCustomer(Long customerId);

    void deleteCustomer(Customer customer);

    Customer updateCustomer(Customer customer, Address homeAddress);

    List<Customer> findCustomers();
}