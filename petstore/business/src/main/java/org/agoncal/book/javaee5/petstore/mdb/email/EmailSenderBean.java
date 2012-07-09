package org.agoncal.book.javaee5.petstore.mdb.email;

import org.agoncal.book.javaee5.petstore.entity.order.Order;
import org.agoncal.book.javaee5.petstore.entity.order.OrderLine;
import org.agoncal.book.javaee5.petstore.util.Constants;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 */
@MessageDriven(mappedName = "jms/topic/order", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "clientId", propertyValue = "emailSenderID"),
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "EmailSender")
}
)
public class EmailSenderBean implements MessageListener {

    // ======================================
    // =             Attributes             =
    // ======================================

    private final String cname = this.getClass().getName();
    private Logger logger = Logger.getLogger(Constants.LOGGER_MDB);

    // ======================================
    // =             Constants              =
    // ======================================

    private static final String SMTP_HOST = "smtp.free.fr";
    private static final String USER = "yaps.petstore";
    private static final String PASSWORD = "yapspwd";

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
                sendEMail(order);
            } else {
                logger.warning("Message of wrong type: Id=" + message.getJMSMessageID() + " class=" + message.getClass().getName());
            }
        } catch (JMSException e) {
            logger.throwing(cname, mname, e);
            // rollback the undergoing transaction if error occured
            throw new EJBException(e);
        } catch (MessagingException e) {
            logger.throwing(cname, mname, e);
            // rollback the undergoing transaction if error occured
            throw new EJBException(e);
        }
        logger.exiting(cname, mname);
    }

    // ======================================
    // =           Private methods          =
    // ======================================

    private void sendEMail(Order order) throws MessagingException {
        final String mname = "createAndSendMail";
        logger.entering(cname, mname, order);

        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, null);
        logger.fine("session==null ? " + (session == null));

        javax.mail.Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("no-reply@antoniogoncalves.org"));
        String email = order.getCustomer().getEmail();
        logger.fine("email==null ? " + (email == null));

        msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(email, false));
        msg.setSubject(formatSubject(order));
        msg.setText(formatBody(order));
        msg.setSentDate(new Date());

        Transport transport = session.getTransport("smtp");
        try {
            transport.connect(SMTP_HOST, USER, PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (MessagingException me) {
            logger.throwing(cname, mname, me);
        } finally {
            try {
                transport.close();
            } catch (MessagingException e) {
                logger.warning("Exception occured while closing javax.mail.Transport: " + e.getMessage());
            }
        }
        logger.exiting(cname, mname);
    }

    private String formatSubject(Order order) {
        return "[YAPS] Confirmation: Order #" + order.getId();
    }

    private String formatBody(Order order) {
        StringBuffer sb = new StringBuffer();
        sb.append("Dear ");
        sb.append(order.getCustomer().getFirstname());
        sb.append(",\n your order #");
        sb.append(order.getId());
        sb.append(" has been successfully placed.");

        sb.append("Your shopping cart content is:\n");
        for (OrderLine line : order.getOrderLines()) {
            sb.append("\t");
            sb.append(line.getItem().getName());
            sb.append(" x");
            sb.append(line.getQuantity());
            sb.append("\n");
        }
        sb.append("\n\n");
        sb.append("Looking forward to serve you again,");
        sb.append("\n\nThe YAPS team.");
        return sb.toString();
    }
}