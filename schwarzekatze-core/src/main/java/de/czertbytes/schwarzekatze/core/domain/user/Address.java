package de.czertbytes.schwarzekatze.core.domain.user;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 128)
    private String street;

    @Column(length = 16)
    private String streetNumber;

    @Column(length = 128)
    private String special;

    @Column(length = 16)
    private String postalCode;

    @Column(length = 128)
    private String city;

    @Enumerated(value = EnumType.STRING)
    private Country country;

    public Address() {
    }

    public Address(String street, String streetNumber, String special, String postalCode, String city, Country country) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.special = special;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
