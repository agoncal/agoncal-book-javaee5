package org.agoncal.book.javaee5.petstore.jsf;

import org.agoncal.book.javaee5.petstore.entity.Address;
import org.agoncal.book.javaee5.petstore.entity.customer.Customer;
import org.agoncal.book.javaee5.petstore.stateless.customer.CustomerLocal;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * @author Antonio Goncalves
 */
public class AccountController extends Controller {

    // ======================================
    // =             Attributes             =
    // ======================================
    @EJB
    private CustomerLocal customerBean;

    private final String cname = this.getClass().getName();

    private String login;
    private String password;
    private String password2;
    private Customer customer = new Customer();
    private Address homeAddress = new Address();

    // ======================================
    // =             Constants              =
    // ======================================

    // ======================================
    // =            Constructors            =
    // ======================================

    // ======================================
    // =          Business methods          =
    // ======================================
    public String doSignIn() {
        final String mname = "doSignIn";
        logger.entering(cname, mname);

        String navigateTo = null;

        try {
            customer = customerBean.authenticate(login, password);
            homeAddress = customer.getHomeAddress();
            navigateTo = "customer.signed.in";
        } catch (Exception e) {
            addMessage(cname, mname, e);
        }

        logger.exiting(cname, mname, navigateTo);
        return navigateTo;
    }

    public String doCreateNewAccount() {
        final String mname = "doCreateNewAccount";
        logger.entering(cname, mname);

        String navigateTo = null;

        // Id and password must be filled
        if ("".equals(customer.getLogin()) || "".equals(customer.getPassword()) || "".equals(password2)) {
            addWarningMessage("Id and passwords have to be filled");
            navigateTo = null;

        } else if (!customer.getPassword().equals(password2)) {
            addWarningMessage("Both entered passwords have to be the same");
            navigateTo = null;

        } else {
            navigateTo = "create.a.new.account";
        }

        logger.exiting(cname, mname, navigateTo);
        return navigateTo;
    }

    public String doCreateCustomer() {
        final String mname = "doCreateCustomer";
        logger.entering(cname, mname);

        String navigateTo = null;

        try {
            // Creates the customer
            customer = customerBean.createCustomer(customer, homeAddress);
            homeAddress = customer.getHomeAddress();
            navigateTo = "customer.created";
        } catch (Exception e) {
            addMessage(cname, mname, e);
        }

        logger.exiting(cname, mname, navigateTo);
        return navigateTo;
    }

    public String doUpdateAccount() {
        final String mname = "doUpdateAccount";
        logger.entering(cname, mname);

        String navigateTo = null;

        try {
            // Updates the customer
            customer = customerBean.updateCustomer(customer, homeAddress);
            homeAddress = customer.getHomeAddress();
            navigateTo = "account.updated";
        } catch (Exception e) {
            addMessage(cname, mname, e);
        }

        logger.exiting(cname, mname, navigateTo);
        return navigateTo;
    }

    public String doSignOff() {
        final String mname = "doSignOff";
        logger.entering(cname, mname);

        String navigateTo = "main";

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();

        logger.exiting(cname, mname, navigateTo);
        return navigateTo;
    }

    // ======================================
    // =          Protected methods         =
    // ======================================

    // ======================================
    // =          Getters & Setters         =
    // ======================================
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    // ======================================
    // =         hash, equals, toString     =
    // ======================================
}