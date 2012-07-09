package org.agoncal.book.javaee5.petstore.locator;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 *         This unchecked exception is throw when a location problem occurs.
 */
public class ServiceLocatorException extends RuntimeException {

    // ======================================
    // =            Constructors            =
    // ======================================

    public ServiceLocatorException(Throwable cause) {
        super(cause);
    }

}