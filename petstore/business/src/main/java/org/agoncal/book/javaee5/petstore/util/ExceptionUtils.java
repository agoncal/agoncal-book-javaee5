package org.agoncal.book.javaee5.petstore.util;

import org.agoncal.book.javaee5.petstore.exception.CreditCardException;
import org.agoncal.book.javaee5.petstore.exception.ValidationException;

import javax.ejb.EJBException;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 */
public class ExceptionUtils {

    // ======================================
    // =          Business methods          =
    // ======================================

    public static Throwable getRootCause(Throwable throwable) {
        Throwable cause;

        if (throwable instanceof EJBException) {
            cause = ((EJBException) throwable).getCausedByException();
        } else {
            cause = throwable.getCause();
        }

        if (cause != null) {
            throwable = cause;
            while ((throwable = throwable.getCause()) != null) {
                cause = throwable;
            }
        }
        return cause;
    }

    public static boolean isApplicationException(Throwable throwable) {
        if (throwable instanceof ValidationException) {
            return true;
        } else if (throwable instanceof CreditCardException) {
            return true;
        } else {
            return false;
        }
    }
}