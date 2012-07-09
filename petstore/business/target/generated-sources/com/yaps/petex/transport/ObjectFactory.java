
package com.yaps.petex.transport;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.yaps.petex.transport package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DeliverItems_QNAME = new QName("http://transport.petex.yaps.com/", "deliverItems");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.yaps.petex.transport
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeliverItems }
     * 
     */
    public DeliverItems createDeliverItems() {
        return new DeliverItems();
    }

    /**
     * Create an instance of {@link DeliveryPlace }
     * 
     */
    public DeliveryPlace createDeliveryPlace() {
        return new DeliveryPlace();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeliverItems }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://transport.petex.yaps.com/", name = "deliverItems")
    public JAXBElement<DeliverItems> createDeliverItems(DeliverItems value) {
        return new JAXBElement<DeliverItems>(_DeliverItems_QNAME, DeliverItems.class, null, value);
    }

}
