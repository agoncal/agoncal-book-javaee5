package org.agoncal.book.javaee5.barkbank.validator;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Calendar;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 *         Can be tested at http://localhost:8080/barkbank/ValidationService?Tester
 *         WSDL at http://localhost:8080/barkbank/ValidationService?WSDL
 */
@WebService
public class Validation {

    // ======================================
    // =             Attributes             =
    // ======================================

    private Logger logger = Logger.getLogger("com.barkbank.validator");
    private final String cname = this.getClass().getName();

    // ======================================
    // =             Constants              =
    // ======================================
    public static final String INVALID_NUMBER = "Number is empty";
    public static final String INVALID_TYPE = "Type is empty";
    public static final String INVALID_EXPIRY_DATE = "Expiry date is empty";
    public static final String INVALID_YEAR = "The year of the credit card is passed";
    public static final String INVALID_MONTH = "The month of the credit card is passed";
    public static final String INVALID_VISA = "Visa card number has to be even";
    public static final String CARD_OK = "OK";

    // ======================================
    // =          Business methods          =
    // ======================================

    @WebMethod(operationName = "ValidateCard")
    @WebResult(name = "cardStatus")
    public String validateCreditCard(@WebParam(name = "creditCardNumber") String ccNumber,
                                     @WebParam(name = "creditCardType") String ccType,
                                     @WebParam(name = "expiryDate") String ccExpiryDate) {
        final String mname = "validateCreditCard";
        logger.entering(cname, mname, new Object[]{ccNumber, ccType, ccExpiryDate});

        if (ccNumber == null || "".equals(ccNumber))
            return INVALID_NUMBER;
        if (ccType == null || "".equals(ccType))
            return INVALID_TYPE;
        if (ccExpiryDate == null || "".equals(ccExpiryDate))
            return INVALID_EXPIRY_DATE;

        Calendar calendar = Calendar.getInstance();

        int year = getExpiryYear(ccExpiryDate);
        int month = getExpiryMonth(ccExpiryDate);
        int lastNumber = getLastNumber(ccNumber);
        logger.finest("Year/Month=" + year + "/" + month);
        logger.finest("Last Number=" + lastNumber);

        // The year of the credit is passed
        if (year < calendar.get(Calendar.YEAR)) {
            logger.info(INVALID_YEAR);
            return INVALID_YEAR;
        }

        // The year of the credit is the actual year but the month is older
        if (year == calendar.get(Calendar.YEAR) && month < calendar.get(Calendar.MONTH)) {
            logger.info(INVALID_MONTH);
            return INVALID_MONTH;
        }

        // For a Visa card, its number has to be even
        if ("visa".equalsIgnoreCase(ccType) && lastNumber % 2 != 0) {
            logger.info(INVALID_VISA);
            return INVALID_VISA;
        }

        return CARD_OK;
    }

    // ======================================
    // =           Private Methods          =
    // ======================================

    private int getExpiryMonth(String ccExpiryDate) {
        if (ccExpiryDate != null) {
            // get the slash and return the text before it
            final int slashStart = ccExpiryDate.indexOf("/");
            if (slashStart != -1) return parseInt(ccExpiryDate.substring(0, slashStart));
        }
        return 1;
    }

    // The year is represented with only two figures, we add 2000 (eg. '05' -> '2005')
    private int getExpiryYear(String ccExpiryDate) {
        if (ccExpiryDate != null) {
            // get the slash and return the text after it
            final int slashStart = ccExpiryDate.indexOf("/");
            if (slashStart != -1)
                return parseInt(ccExpiryDate.substring(slashStart + 1, ccExpiryDate.length())) + 2000;
        }
        return 2000;
    }

    private int getLastNumber(String ccNumber) {
        return parseInt(ccNumber.substring(ccNumber.length() - 1, ccNumber.length()));
    }
}