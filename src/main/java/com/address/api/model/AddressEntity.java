package com.address.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_address", schema = "DBADDRESS")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ADDRESS", columnDefinition = "int")
    private Long id;

    @Column(name = "ZIPCODE", nullable = false, length = 13)
    private String zipCode;

    @Column(name = "CITY", nullable = false, length = 40)
    private String city;

    @Column(name = "NEIGHBORHOOD", length = 70)
    private String neighborhood;

    @Column(name = "STATE", nullable = false, length = 30)
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long idAddress) {
        this.id = idAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String cep) {
        this.zipCode = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
