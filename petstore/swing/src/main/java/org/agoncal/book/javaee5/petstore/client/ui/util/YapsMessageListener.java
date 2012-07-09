package org.agoncal.book.javaee5.petstore.client.ui.util;

import org.agoncal.book.javaee5.petstore.util.Constants;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The YapsJmsListener class.
 * <p/>
 * Cette classe impl�mente l'interface javax.jms.MessageListener et agr�ge un YapsTableModel.
 * Elle s'attend � recevoir un message de type javax.jms.ObjectMessage.
 * A chaque r�ception d'un message l'objet re�u est ajout� au model.
 *
 * @author Alexis Midon
 * @see javax.jms.MessageListener
 * @see javax.jms.ObjectMessage
 * @see YapsTableModel#add(Object)
 */
public class YapsMessageListener implements MessageListener {

    private Logger logger = Logger.getLogger(Constants.LOGGER_CLIENT);
    private final String cname = this.getClass().getName();

    private YapsTableModel tableModel;


    public YapsMessageListener() {
    }

    public YapsMessageListener(YapsTableModel tableModel) {
        this.tableModel = tableModel;
    }


    public YapsTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(YapsTableModel tableModel) {
        this.tableModel = tableModel;
    }

    @SuppressWarnings({"unchecked"})
    public void onMessage(Message message) {
        String mname = "onMessage";
        if (message instanceof ObjectMessage) {
            ObjectMessage objMsg = (ObjectMessage) message;
            try {
                if (logger.isLoggable(Level.FINE)) {
                    Enumeration names = objMsg.getPropertyNames();
                    logger.fine("Message Properties are:");
                    while (names.hasMoreElements()) {
                        String propName = (String) names.nextElement();
                        logger.fine(propName + ":" + objMsg.getObjectProperty(propName).toString());
                    }
                }
                tableModel.add(objMsg.getObject());
            } catch (JMSException e) {
                logger.throwing(cname, mname, e);
            }
        } else {
            logger.warning("Message of unexpected type received!");
        }
    }
}
