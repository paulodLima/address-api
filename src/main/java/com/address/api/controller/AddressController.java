package com.address.api.controller;

import com.address.api.dto.AddressDTO;
import com.address.api.model.AddressEntity;
import com.address.api.model.filter.AddressFilter;
import com.address.api.service.AddressService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;


    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    public AddressDTO getAddress(@PathVariable Long id){
        return addressService.getAddressDTO(id);
    }

    @PostMapping
    public AddressDTO save(@RequestBody @Valid AddressDTO addressDTO){
        return  addressService.processAddress(addressDTO);
    }

    @GetMapping
    public List<AddressEntity> getAddressAll(AddressFilter addressFilter, Pageable pageable, Integer page, Integer size) {
        return addressService.getAddressAll(addressFilter,pageable);
    }
}
