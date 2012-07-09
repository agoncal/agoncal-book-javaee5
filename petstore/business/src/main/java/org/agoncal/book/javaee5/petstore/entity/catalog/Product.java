package org.agoncal.book.javaee5.petstore.entity.catalog;

import org.agoncal.book.javaee5.petstore.exception.ValidationException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 * @see Category
 * @see Item
 */

@Entity
@Table(name = "t_product")
public class Product implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 30)
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_fk", nullable = false)
    private Category category;
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @OrderBy("name ASC")
    private List<Item> items;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Product() {
    }

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PrePersist
    @PreUpdate
    private void validateData() {
        if (name == null || "".equals(name))
            throw new ValidationException("Invalid name");
        if (description == null || "".equals(description))
            throw new ValidationException("Invalid description");
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!description.equals(product.description)) return false;
        if (!id.equals(product.id)) return false;
        if (!name.equals(product.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Product");
        sb.append("{id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", category=").append(category);
        sb.append(", items=").append(items == null ? 0 : items.size());
        sb.append('}');
        return sb.toString();
    }
}