package org.agoncal.book.javaee5.petstore.exception;

import javax.ejb.ApplicationException;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 */
@ApplicationException(rollback = true)
public class ValidationException extends RuntimeException {

    // ======================================
    // =            Constructors            =
    // ======================================

    public ValidationException(String message) {
        super(message);
    }
}