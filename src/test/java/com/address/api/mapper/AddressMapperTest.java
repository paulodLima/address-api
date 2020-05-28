package com.address.api.mapper;

import com.address.api.model.AddressEntity;
import com.address.api.dto.AddressDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.spy;

class AddressMapperTest {

    private AddressMapper addressMapper = Mappers.getMapper(AddressMapper.class);

    @Test
    void toAddressResponseDto() {

        AddressEntity addressEntity = spy(AddressEntity.class);
        addressEntity.setZipCode("73080-100");
        addressEntity.setNeighborhood("Setor de Mansões de Sobradinho");
        addressEntity.setCity("Brasília");
        addressEntity.setState("DF");

        AddressDTO addressDTO = addressMapper.toAddressDTO(addressEntity);

        assertEquals("73080-100", addressDTO.getZipCode());
        assertEquals("Setor de Mansões de Sobradinho", addressDTO.getNeighborhood());
        assertEquals("Brasília", addressDTO.getCity());
        assertEquals("DF", addressDTO.getState());


    }

    @Test
    void toAddressRequestDtoWithNullParam() {

        AddressDTO addressDTO = addressMapper.toAddressDTO(null);

        assertNull(addressDTO);


    }

    @Test
    void toAddressEntity() {

        AddressDTO addressDTO = spy(AddressDTO.class);
        addressDTO.setZipCode("70384-080");
        addressDTO.setNeighborhood("Sqs 315 bloco h");
        addressDTO.setCity("Brasília");
        addressDTO.setState("DF");

        AddressEntity addressEntity = addressMapper.toAddressEntity(addressDTO);

        assertNull(addressEntity.getId());
        assertEquals("70384-080", addressEntity.getZipCode());
        assertEquals("Sqs 315 bloco h", addressEntity.getNeighborhood());
        assertEquals("Brasília", addressEntity.getCity());
        assertEquals("DF", addressEntity.getState());


    }


    @Test
    void toAddressEntityWithAddressNull() {
        AddressDTO addressDTO = spy(AddressDTO.class);
        AddressEntity addressEntity = addressMapper.toAddressEntity(addressDTO);

        assertNull(addressEntity.getZipCode());
        assertNull(addressEntity.getNeighborhood());
        assertNull(addressEntity.getCity());
        assertNull(addressEntity.getState());
    }


    @Test
    void toAddressEntityWithCommerceNull() {

        AddressDTO addressDTO = spy(AddressDTO.class);
        addressDTO.setZipCode("70384-080");
        addressDTO.setNeighborhood("Sqs 315 bloco h");
        addressDTO.setCity("Brasília");
        addressDTO.setState("DF");


        AddressEntity addressEntity = addressMapper.toAddressEntity(addressDTO);

        assertEquals("70384-080", addressEntity.getZipCode());
        assertEquals("Sqs 315 bloco h", addressEntity.getNeighborhood());
        assertEquals("Brasília", addressEntity.getCity());
        assertEquals("DF", addressEntity.getState());
    }


    @Test
    void toAddressEntityWithNullParams() {

        AddressEntity addressEntity = addressMapper.toAddressEntity(null);

        assertNull(addressEntity);
    }


}