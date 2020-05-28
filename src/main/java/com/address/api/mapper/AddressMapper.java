package com.address.api.mapper;

import com.address.api.dto.AddressDTO;
import com.address.api.model.AddressEntity;
import org.mapstruct.Mapper;

import java.util.Optional;


@Mapper(componentModel = "spring")
public abstract class AddressMapper {

    public abstract AddressDTO toAddressDTO(AddressEntity addressEntity);

    public abstract AddressEntity toAddressEntity(AddressDTO addressDTO);

}
