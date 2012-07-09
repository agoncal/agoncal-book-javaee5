package org.agoncal.book.javaee5.petstore.delegate;

import org.agoncal.book.javaee5.petstore.entity.Address;
import org.agoncal.book.javaee5.petstore.entity.customer.Customer;
import org.agoncal.book.javaee5.petstore.locator.ServiceLocator;
import org.agoncal.book.javaee5.petstore.stateless.customer.CustomerRemote;

import java.util.List;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 *         This class follows the Delegate design pattern. It's a one to one method
 *         with the CustomerBean class. Each method delegates the call to the
 *         CustomerBean class
 */
public final class CustomerDelegate {

    // ======================================
    // =           Business methods         =
    // ======================================

    public static Customer createCustomer(Customer customer, Address address) {
        return getCustomerRemote().createCustomer(customer, address);
    }

    public static Customer findCustomer(Long customerId) {
        return getCustomerRemote().findCustomer(customerId);
    }

    public static void deleteCustomer(Customer customer) {
        getCustomerRemote().deleteCustomer(customer);
    }

    public static Customer updateCustomer(Customer customer, Address address) {
        return getCustomerRemote().updateCustomer(customer, address);
    }

    public static List<Customer> findCustomers() {
        return getCustomerRemote().findCustomers();
    }

    // ======================================
    // =            Private methods         =
    // ======================================

    private static CustomerRemote getCustomerRemote() {
        CustomerRemote customerRemote;
        customerRemote = (CustomerRemote) ServiceLocator.getInstance().getRemoteInterface("ejb/stateless/Customer");
        return customerRemote;
    }
}
