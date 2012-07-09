
package org.agoncal.book.javaee5.petex.transport.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.agoncal.book.javaee5.petex.transport.DeliveryPlace;

@XmlRootElement(name = "deliverItems", namespace = "http://transport.petex.javaee5.book.agoncal.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deliverItems", namespace = "http://transport.petex.javaee5.book.agoncal.org/", propOrder = {
    "arg0",
    "arg1",
    "arg2"
})
public class DeliverItems {

    @XmlElement(name = "arg0", namespace = "")
    private DeliveryPlace arg0;
    @XmlElement(name = "arg1", namespace = "")
    private DeliveryPlace arg1;
    @XmlElement(name = "arg2", namespace = "")
    private String arg2;

    /**
     * 
     * @return
     *     returns DeliveryPlace
     */
    public DeliveryPlace getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(DeliveryPlace arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns DeliveryPlace
     */
    public DeliveryPlace getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(DeliveryPlace arg1) {
        this.arg1 = arg1;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg2() {
        return this.arg2;
    }

    /**
     * 
     * @param arg2
     *     the value for the arg2 property
     */
    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

}
