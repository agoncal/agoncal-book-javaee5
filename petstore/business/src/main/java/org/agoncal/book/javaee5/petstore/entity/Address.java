package org.agoncal.book.javaee5.petstore.entity;

import org.agoncal.book.javaee5.petstore.exception.ValidationException;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Antonio Goncalves
 *         Eyrolles Book - Les Cahiers du Programmeur - Java EE 5
 *         http://www.eyrolles.com/Informatique/Livre/java-ee-5-9782212126587
 *         http://www.antoniogoncalves.org
 *         --
 * @see org.agoncal.book.javaee5.petstore.entity.customer.Customer
 * @see org.agoncal.book.javaee5.petstore.entity.order.Order
 */

@Entity
@Table(name = "t_address")
public class Address implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String street1;
    private String street2;
    @Column(nullable = false, length = 100)
    private String city;
    private String state;
    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipcode;
    @Column(nullable = false, length = 50)
    private String country;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Address() {
    }

    public Address(String street1, String city, String zipcode, String country) {
        this.street1 = street1;
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
    }

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PrePersist
    @PreUpdate
    private void validateData() {
        if (street1 == null || "".equals(street1))
            throw new ValidationException("Invalid street");
        if (city == null || "".equals(city))
            throw new ValidationException("Invalid city");
        if (zipcode == null || "".equals(zipcode))
            throw new ValidationException("Invalid zip code");
        if (country == null || "".equals(country))
            throw new ValidationException("Invalid country");
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Long getId() {
        return id;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // ======================================
    // =         hash, equals, toString     =
    // ======================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!city.equals(address.city)) return false;
        if (!country.equals(address.country)) return false;
        if (!id.equals(address.id)) return false;
        if (state != null ? !state.equals(address.state) : address.state != null) return false;
        if (!street1.equals(address.street1)) return false;
        if (street2 != null ? !street2.equals(address.street2) : address.street2 != null) return false;
        if (!zipcode.equals(address.zipcode)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + street1.hashCode();
        result = 31 * result + (street2 != null ? street2.hashCode() : 0);
        result = 31 * result + city.hashCode();
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + zipcode.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Address");
        sb.append("{id=").append(id);
        sb.append(", street1='").append(street1).append('\'');
        sb.append(", street2='").append(street2).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", zipcode='").append(zipcode).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
