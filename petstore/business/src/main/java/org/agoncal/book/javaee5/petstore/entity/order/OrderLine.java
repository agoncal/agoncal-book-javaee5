package org.agoncal.book.javaee5.petstore.entity.order;

import org.agoncal.book.javaee5.petstore.entity.catalog.Item;
import org.agoncal.book.javaee5.petstore.exception.ValidationException;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 * @see org.agoncal.book.javaee5.petstore.entity.order.Order
 * @see Item
 */

@Entity
@Table(name = "t_order_line")
public class OrderLine implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Integer quantity;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_fk", nullable = false)
    private Item item;

    // ======================================
    // =            Constructors            =
    // ======================================

    public OrderLine() {
    }

    public OrderLine(Integer quantity, Item item) {
        this.quantity = quantity;
        this.item = item;
    }

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PrePersist
    @PreUpdate
    private void validateData() {
        if (quantity == null || quantity < 0)
            throw new ValidationException("Invalid quantity");
    }

    // ======================================
    // =          Business methods          =
    // ======================================

    public Float getSubTotal() {
        return item.getUnitCost() * quantity;
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderLine orderLine = (OrderLine) o;

        if (!id.equals(orderLine.id)) return false;
        if (!item.equals(orderLine.item)) return false;
        if (!quantity.equals(orderLine.quantity)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + quantity.hashCode();
        result = 31 * result + item.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("OrderLine");
        sb.append("{id=").append(id);
        sb.append(", quantity=").append(quantity);
        sb.append(", item=").append(item);
        sb.append('}');
        return sb.toString();
    }
}
