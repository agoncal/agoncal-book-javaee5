
package org.agoncal.book.javaee5.barkbank.validator.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ValidateCardResponse", namespace = "http://validator.barkbank.javaee5.book.agoncal.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidateCardResponse", namespace = "http://validator.barkbank.javaee5.book.agoncal.org/")
public class ValidateCreditCardResponse {

    @XmlElement(name = "cardStatus", namespace = "")
    private String cardStatus;

    /**
     * 
     * @return
     *     returns String
     */
    public String getCardStatus() {
        return this.cardStatus;
    }

    /**
     * 
     * @param cardStatus
     *     the value for the cardStatus property
     */
    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

}
