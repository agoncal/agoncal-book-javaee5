package org.agoncal.book.javaee5.petstore.mdb.print;

import org.agoncal.book.javaee5.petstore.entity.order.Order;
import org.agoncal.book.javaee5.petstore.entity.order.OrderLine;
import org.agoncal.book.javaee5.petstore.util.Constants;

import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 */
@MessageDriven(mappedName = "jms/topic/order")
public class OrderPrinterBean implements MessageListener {

    // ======================================
    // =             Attributes             =
    // ======================================
//    @Resource
//    private MessageDrivenContext mdc;
//    @Resource(mappedName = "jms/bookJavaEE5ConnectionFactory")
//    private ConnectionFactory connectionFactory;
//    @Resource(mappedName = "jms/queue/legalDpt")
//    private Destination destinationOrder;
//
//    private Connection connection;

    private final String cname = this.getClass().getName();
    private Logger logger = Logger.getLogger(Constants.LOGGER_MDB);

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // ======================================
    // =          Business methods          =
    // ======================================

    public void onMessage(Message message) {
        final String mname = "onMessage";
        logger.entering(cname, mname);
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage msg = (ObjectMessage) message;
                logger.info("Message received! Id=" + msg.getJMSMessageID());
                Order order = (Order) msg.getObject();
                printOrder(order);
            } else {
                logger.warning("Message of wrong type: Id=" + message.getJMSMessageID() + " class=" + message.getClass().getName());
            }
        } catch (JMSException e) {
            logger.throwing(cname, mname, e);
            // rollback the undergoing transaction if error occured
            throw new EJBException(e);
        }
        logger.exiting(cname, mname);
    }

    // ======================================
    // =           Private methods          =
    // ======================================

    private void printOrder(Order order) {
        logger.info("Order # " + order.getId() + " on the " + dateFormat.format(order.getOrderDate()));
        logger.info(order.getCustomer().getFirstname() + order.getCustomer().getLastname() + " bought ");

        for (OrderLine line : order.getOrderLines()) {
            logger.info("\t" + line.getItem().getName() + "*" + line.getQuantity() + "=" + line.getSubTotal());
        }
        logger.info("Total=" + order.getTotal());
    }
}
