package org.agoncal.book.javaee5.petex.transport;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 *         Can be tested at http://localhost:8080/petex/DeliveryService?Tester
 *         WSDL at http://localhost:8080/petex/DeliveryService?WSDL
 */
@WebService
public class Delivery {

    // ======================================
    // =             Attributes             =
    // ======================================

    private Logger logger = Logger.getLogger("com.petex.transport");
    private final String cname = this.getClass().getName();

    // ======================================
    // =          Business methods          =
    // ======================================

    @WebMethod
    @Oneway
    public void deliverItems(DeliveryPlace from, DeliveryPlace to, String reference) {
        final String mname = "deliverItems";
        logger.entering(cname, mname);

        logger.info("Delivery Order Received");
        logger.info("Deliver from " + from);
        logger.info("Deliver to " + to);
        logger.info("Reference n� " + reference);

        logger.exiting(cname, mname);
    }
}