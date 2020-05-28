package com.address.api.dto;

import javax.validation.constraints.NotBlank;

public class FooRequestDTO {

    private Long id;

    @NotBlank
    private String address;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}