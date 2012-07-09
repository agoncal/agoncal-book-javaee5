package org.agoncal.book.javaee5.barkbank.validator;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 */
public class ValidationServiceTest {

    // ======================================
    // =              Test cases            =
    // ======================================

    /**
     * This test ensures that the validation method works
     */

    @Test
    public void shouldValidateSeveralCards() throws Exception {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR) - 2000;
        int month = calendar.get(Calendar.MONTH);
        Validation validator = new Validation();

        assertEquals("OK", Validation.CARD_OK, validator.validateCreditCard("1234", "Visa", "01/" + (year + 1)));
        assertEquals("Invalid VISA", Validation.INVALID_VISA, validator.validateCreditCard("1231", "Visa", "01/18"));
        assertEquals("Invalid Year", Validation.INVALID_YEAR, validator.validateCreditCard("1234", "Visa", "01/" + (year - 1)));
        assertEquals("Invalid Month", Validation.INVALID_MONTH, validator.validateCreditCard("1234", "Visa", (month - 1) + "/" + year));
    }
}