package com.address.api.mapper;

import com.address.api.dto.AddressDTO;
import com.address.api.model.AddressEntity;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-28T11:16:07-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl extends AddressMapper {

    @Override
    public AddressDTO toAddressDTO(AddressEntity addressEntity) {
        if ( addressEntity == null ) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setZipCode( addressEntity.getZipCode() );
        addressDTO.setState( addressEntity.getState() );
        addressDTO.setNeighborhood( addressEntity.getNeighborhood() );
        addressDTO.setCity( addressEntity.getCity() );

        return addressDTO;
    }

    @Override
    public AddressEntity toAddressEntity(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        AddressEntity addressEntity = new AddressEntity();

        addressEntity.setZipCode( addressDTO.getZipCode() );
        addressEntity.setCity( addressDTO.getCity() );
        addressEntity.setNeighborhood( addressDTO.getNeighborhood() );
        addressEntity.setState( addressDTO.getState() );

        return addressEntity;
    }
}
