package org.agoncal.book.javaee5.petstore.stateless.order;

import com.yaps.petex.transport.Delivery;
import com.yaps.petex.transport.DeliveryPlace;
import com.yaps.petex.transport.DeliveryService;
import org.agoncal.book.javaee5.petstore.dto.CartItem;
import org.agoncal.book.javaee5.petstore.entity.Address;
import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.agoncal.book.javaee5.petstore.entity.customer.Customer;
import org.agoncal.book.javaee5.petstore.entity.order.CreditCard;
import org.agoncal.book.javaee5.petstore.entity.order.Order;
import org.agoncal.book.javaee5.petstore.entity.order.OrderLine;
import org.agoncal.book.javaee5.petstore.exception.ValidationException;
import org.agoncal.book.javaee5.petstore.util.Constants;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.ws.WebServiceRef;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 *         This class is a facade for all order services.
 */
@SuppressWarnings(value = "unchecked")
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
@Stateless(name = "OrderSB", mappedName = "ejb/stateless/Order")
public class OrderBean implements OrderRemote, OrderLocal {

    // ======================================
    // =             Attributes             =
    // ======================================

    @PersistenceContext(unitName = "bookJavaEE5PU")
    private EntityManager em;
    //    @Resource
    //    private SessionContext sc;
    @Resource(mappedName = "jms/bookJavaEE5ConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/topic/order")
    private Topic destinationOrder;
    private Connection connection;

    @WebServiceRef(wsdlLocation = "http://localhost:8080/petex/DeliveryService?WSDL")
//    @WebServiceRef
    private DeliveryService deliveryService;


    private final String cname = this.getClass().getName();
    private Logger logger = Logger.getLogger(Constants.LOGGER_STATELESS);

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PostConstruct
    public void openConnection() {
        try {
            connection = connectionFactory.createConnection();
        } catch (JMSException e) {
            logger.throwing(cname, "openConnection", e);
            throw new EJBException(e);
        }
    }

    @PreDestroy
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException e) {
                logger.throwing(cname, "closeConnection", e);
                throw new EJBException(e);
            }
        }
    }

    // ======================================
    // =          Business methods          =
    // ======================================

    public Order createOrder(final Customer customer, final Address deliveryAddress, final CreditCard creditCard, final List<CartItem> cartItems) {
        final String mname = "createOrder";
        logger.entering(cname, mname, cartItems.size());

        if (cartItems == null || cartItems.size() == 0)
            throw new ValidationException("Shopping cart is empty");

        // Create the order
        Order order = new Order(customer, em.merge(deliveryAddress), creditCard);

        // From the shopping cart it creates the items
        List<OrderLine> orderLines = new ArrayList<OrderLine>();

        for (CartItem cartItem : cartItems) {
            orderLines.add(new OrderLine(cartItem.getQuantity(), cartItem.getItem()));
        }
        order.setOrderLines(orderLines);

        // Persist the order
        em.persist(order);

        publishOrder(order);
        notifyTransporter(order);

        logger.exiting(cname, mname, order);
        return order;
    }

    public Order findOrder(final Long orderId) {
        final String mname = "findOrder";
        logger.entering(cname, mname, orderId);

        if (orderId == null)
            throw new ValidationException("Invalid id");

        Order order;

        order = em.find(Order.class, orderId);

        logger.exiting(cname, mname, order);
        return order;
    }

    public void deleteOrder(final Order order) {
        final String mname = "deleteOrder";
        logger.entering(cname, mname, order);

        if (order == null)
            throw new ValidationException("Order object is null");

        em.remove(em.merge(order));

        logger.exiting(cname, mname);
    }

    public List<Order> findOrders() {
        final String mname = "findOrders";
        logger.entering(cname, mname);

        Query query;
        List<Order> orders;

        query = em.createQuery("SELECT o FROM Order o");
        orders = query.getResultList();

        logger.exiting(cname, mname, orders.size());
        return orders;
    }

    // ======================================
    // =           Private Methods          =
    // ======================================

    private void publishOrder(Order order) {
        String mname = "publishOrder";
        logger.entering(cname, mname, order);
        Session session = null;
        try {
            // Opens a JMS Session
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

            // Create a producer
            MessageProducer producer = session.createProducer(destinationOrder);

            // Create a message
            ObjectMessage objectMessage = session.createObjectMessage();
            // Add in header the distinct categories
            Set<Category> categories = Order.getDistinctCategories(order);
            for (Category c : categories) {
                objectMessage.setBooleanProperty(c.getName(), true);
            }
            objectMessage.setObject(order);

            producer.send(objectMessage);
            logger.finest("Message sent in " + destinationOrder);
        } catch (JMSException e) {
            logger.throwing(cname, mname, e);
            // rollback the undergoing transaction if error occured
            throw new EJBException(e);
        } finally {
            try {
                session.close();
            } catch (JMSException e) {
                logger.warning("Exception occured while closing javax.jms.Session: " + e.getMessage());
                throw new EJBException(e);
            }
        }
        logger.exiting(cname, mname);
    }

    private void notifyTransporter(Order order) {
        String mname = "notifyTransporter";
        logger.entering(cname, mname, order);

        DeliveryPlace from = new DeliveryPlace();
        DeliveryPlace to = new DeliveryPlace();

        from.setContact(Constants.COMPANY_NAME);
        from.setStreet(Constants.COMPANY_STREET);
        from.setCity(Constants.COMPANY_CITY);
        from.setState(Constants.COMPANY_STATE);
        from.setZipcode(Constants.COMPANY_ZIPCODE);
        from.setCountry(Constants.COMPANY_COUNTRY);

        to.setContact(order.getCustomer().getLastname());
        to.setStreet(order.getDeliveryAddress().getStreet1());
        to.setCity(order.getDeliveryAddress().getCity());
        to.setState(order.getDeliveryAddress().getState());
        to.setZipcode(order.getDeliveryAddress().getZipcode());
        to.setCountry(order.getDeliveryAddress().getCountry());

        Delivery delivery = deliveryService.getDeliveryPort();
        delivery.deliverItems(from, to, order.getId().toString());
        logger.info("Delivery Service has been called. ");

        logger.exiting(cname, mname);
    }
}
