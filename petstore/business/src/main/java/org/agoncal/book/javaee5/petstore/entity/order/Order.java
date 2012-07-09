package org.agoncal.book.javaee5.petstore.entity.order;

import org.agoncal.book.javaee5.petstore.entity.Address;
import org.agoncal.book.javaee5.petstore.entity.catalog.Category;
import org.agoncal.book.javaee5.petstore.entity.customer.Customer;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 * @see org.agoncal.book.javaee5.petstore.entity.Address
 * @see Customer
 * @see OrderLine
 */

@Entity
@Table(name = "t_order")
@XmlRootElement
public class Order implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "order_date", updatable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_fk", nullable = false)
    private Customer customer;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "t_order_order_line",
            joinColumns = {@JoinColumn(name = "order_fk")},
            inverseJoinColumns = {@JoinColumn(name = "order_line_fk")})
    private List<OrderLine> orderLines;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_fk", nullable = false)
    private Address deliveryAddress;
    @Embedded
    private CreditCard creditCard = new CreditCard();

    // ======================================
    // =            Constructors            =
    // ======================================

    public Order() {
    }

    public Order(Customer customer, Address deliveryAddress, CreditCard creditCard) {
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
        this.creditCard = creditCard;
    }

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PrePersist
    private void setDefaultData() {
        orderDate = new Date();
    }

    // ======================================
    // =          Business methods          =
    // ======================================

    public Float getTotal() {
        if (orderLines == null || orderLines.isEmpty())
            return 0f;

        Float total = 0f;

        // sum up the quantities
        for (OrderLine orderLine : orderLines) {
            total += (orderLine.getSubTotal());
        }

        return total;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Long getId() {
        return id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public String getCreditCardNumber() {
        return creditCard.getCreditCardNumber();
    }

    public void setCreditCardNumber(String creditCardNumber) {
        creditCard.setCreditCardNumber(creditCardNumber);
    }

    public String getCreditCardType() {
        return creditCard.getCreditCardType();
    }

    public void setCreditCardType(String creditCardType) {
        creditCard.setCreditCardType(creditCardType);
    }

    public String getCreditCardExpiryDate() {
        return creditCard.getCreditCardExpDate();
    }

    public void setCreditCardExpiryDate(String creditCardExpiryDate) {
        creditCard.setCreditCardExpDate(creditCardExpiryDate);
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (!id.equals(order.id)) return false;
        if (!orderDate.equals(order.orderDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + orderDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Order");
        sb.append("{id=").append(id);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", customer=").append(customer);
        sb.append(", orderLines=").append(orderLines == null ? 0 : orderLines.size());
        sb.append(", deliveryAddress=").append(deliveryAddress);
        sb.append(", creditCard=").append(creditCard);
        sb.append('}');
        return sb.toString();
    }

    public static Set<Category> getDistinctCategories(Order order) {
        Set<Category> catSet = new HashSet<Category>();
        for (OrderLine line : order.getOrderLines()) {
            catSet.add(line.getItem().getProduct().getCategory());
        }
        return catSet;
    }
}
