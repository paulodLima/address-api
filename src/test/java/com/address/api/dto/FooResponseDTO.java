package com.address.api.dto;

public class FooResponseDTO extends FooRequestDTO {

    public FooResponseDTO() {
    }

    public FooResponseDTO(FooRequestDTO fooRequestDTO) {
        this.setAddress(fooRequestDTO.getAddress());
        this.setId(fooRequestDTO.getId());
    }
}
