package org.agoncal.book.javaee5.petstore.entity.order;

import org.agoncal.book.javaee5.petstore.exception.ValidationException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 * @see org.agoncal.book.javaee5.petstore.entity.customer.Customer
 * @see Order
 */

@Embeddable
public class CreditCard implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Column(name = "credit_card_number", length = 30)
    private String creditCardNumber;
    @Column(name = "credit_card_type")
    private String creditCardType;
    @Column(name = "credit_card_expiry_date", length = 5)
    private String creditCardExpDate;

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PrePersist
    @PreUpdate
    private void validateData() {
        if (creditCardNumber == null || "".equals(creditCardNumber))
            throw new ValidationException("Invalid credit card number");
        if (creditCardType == null || "".equals(creditCardType))
            throw new ValidationException("Invalid credit card type");
        if (creditCardExpDate == null || "".equals(creditCardExpDate))
            throw new ValidationException("Invalid credit card expiry date");
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getCreditCardExpDate() {
        return creditCardExpDate;
    }

    public void setCreditCardExpDate(String creditCardExpDate) {
        this.creditCardExpDate = creditCardExpDate;
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        if (!creditCardExpDate.equals(that.creditCardExpDate)) return false;
        if (!creditCardNumber.equals(that.creditCardNumber)) return false;
        if (creditCardType != that.creditCardType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = creditCardNumber.hashCode();
        result = 31 * result + creditCardType.hashCode();
        result = 31 * result + creditCardExpDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CreditCard");
        sb.append("{creditCardNumber='").append(creditCardNumber).append('\'');
        sb.append(", creditCardType=").append(creditCardType);
        sb.append(", creditCardExpiryDate='").append(creditCardExpDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}