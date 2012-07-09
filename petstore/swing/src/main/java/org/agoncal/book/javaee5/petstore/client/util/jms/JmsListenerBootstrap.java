package org.agoncal.book.javaee5.petstore.client.util.jms;

import org.agoncal.book.javaee5.petstore.locator.ServiceLocator;
import org.agoncal.book.javaee5.petstore.util.Constants;

import javax.jms.*;
import java.util.logging.Logger;

/**
 * The JmsListenerBootstrap class.
 * <p/>
 * Cette classe permet de masquer toutes les �tapes n�cessaires � l'ajout d'un listener sur une queue ou un topic JMS.
 *
 * @author Alexis Midon
 */
public class JmsListenerBootstrap {

    private Logger logger = Logger.getLogger(Constants.LOGGER_CLIENT);
    private final String cname = this.getClass().getName();

    private String conFactoryName;
    private String destinationName;
    private String messageSelector;
    private MessageListener messageListener;

    private Connection connection;
    private Session session;
    private MessageConsumer consumer;

    /**
     * @param conFactoryName,  le nom jndi de la javax.jms.ConnectionFactory - obligatoire
     * @param destinationName, le nom jndi de la javax.jms.Destination - obligatoire
     * @param messageSelector, un message selector - optionel
     * @param messageListener, le listener qui sera notifi� - obligatoire
     */
    public JmsListenerBootstrap(String conFactoryName, String destinationName, String messageSelector, MessageListener messageListener) {
        this.conFactoryName = conFactoryName;
        this.destinationName = destinationName;
        this.messageSelector = messageSelector;
        this.messageListener = messageListener;
    }


    public JmsListenerBootstrap(String conFactoryName, String destinationName, MessageListener messageListener) {
        this(conFactoryName, destinationName, null, messageListener);
    }

    /**
     * Cr�e toutes les ressources JMS n�cessaires � la r�ception des messages
     */
    public void initJMS() throws JMSException {
        ConnectionFactory connectionFactory = ServiceLocator.getInstance().getConnectionFactory(conFactoryName);
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = ServiceLocator.getInstance().getDestination(destinationName);
        consumer = session.createConsumer(destination, messageSelector);
        consumer.setMessageListener(messageListener);
    }

    /**
     * Lib�re toutes les ressources JMS utilis�es.
     */
    public void shutdownJMS() {
        String mname = "shutdownJMS";
        try {
            stopListening();
        } catch (JMSException e) {
            logger.throwing(cname, mname, e);
        }
        try {
            consumer.close();
        } catch (JMSException e) {
            logger.throwing(cname, mname, e);
        }
        try {
            session.close();
        } catch (JMSException e) {
            logger.throwing(cname, mname, e);
        }
        try {
            connection.close();
        } catch (JMSException e) {
            logger.throwing(cname, mname, e);
        }
    }

    /**
     * D�marre la r�ception des messages.
     *
     * @throws JMSException
     * @see javax.jms.Connection#start()
     */
    public void startListening() throws JMSException {
        connection.start();
    }

    /**
     * Arr�te la r�ception des messages.
     *
     * @throws JMSException
     * @see javax.jms.Connection#stop()
     */
    public void stopListening() throws JMSException {
        connection.stop();
    }

}