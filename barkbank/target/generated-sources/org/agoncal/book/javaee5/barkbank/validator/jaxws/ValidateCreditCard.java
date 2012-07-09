
package org.agoncal.book.javaee5.barkbank.validator.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ValidateCard", namespace = "http://validator.barkbank.javaee5.book.agoncal.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidateCard", namespace = "http://validator.barkbank.javaee5.book.agoncal.org/", propOrder = {
    "creditCardNumber",
    "creditCardType",
    "expiryDate"
})
public class ValidateCreditCard {

    @XmlElement(name = "creditCardNumber", namespace = "")
    private String creditCardNumber;
    @XmlElement(name = "creditCardType", namespace = "")
    private String creditCardType;
    @XmlElement(name = "expiryDate", namespace = "")
    private String expiryDate;

    /**
     * 
     * @return
     *     returns String
     */
    public String getCreditCardNumber() {
        return this.creditCardNumber;
    }

    /**
     * 
     * @param creditCardNumber
     *     the value for the creditCardNumber property
     */
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getCreditCardType() {
        return this.creditCardType;
    }

    /**
     * 
     * @param creditCardType
     *     the value for the creditCardType property
     */
    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getExpiryDate() {
        return this.expiryDate;
    }

    /**
     * 
     * @param expiryDate
     *     the value for the expiryDate property
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

}
