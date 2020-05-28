package com.address.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddressDTO {
    @NotBlank
    @Size(min = 8, max = 11)
    private String zipCode;
    @NotBlank
    @Size(max = 40)
    private String state;
    @Size(max = 70)
    private String neighborhood;
    @NotBlank
    @Size(min = 2, max = 30)
    private String city;

    public AddressDTO(@NotBlank @Size(min = 8, max = 11) String zipCode, @NotBlank @Size(max = 40) String state, @Size(max = 70) String neighborhood, @NotBlank @Size(min = 2, max = 30) String city) {
        this.zipCode = zipCode;
        this.state = state;
        this.neighborhood = neighborhood;
        this.city = city;
    }
    public AddressDTO(){}

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
