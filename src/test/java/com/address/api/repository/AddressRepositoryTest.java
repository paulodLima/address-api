package com.address.api.repository;

import com.address.api.dto.AddressDTO;
import com.address.api.model.AddressEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AddressRepositoryTest {

    @Autowired
    private AddressRepository repository;

    @Test
    void findByIdAddress() {

        Optional<AddressEntity> optionalAddressDTO = repository.findById(1L);

        assertTrue(optionalAddressDTO.isPresent());

        AddressEntity addressDTO = optionalAddressDTO.get();

        assertEquals("BRASILIA", addressDTO.getCity());
        assertEquals("ASA SUL", addressDTO.getNeighborhood());
        assertEquals("72954023", addressDTO.getZipCode());
        assertEquals("DF", addressDTO.getState());


    }


    @Test
    void findByIdAddressWithoutResult() {

        Optional<AddressEntity> optionalAddressDTO = repository.findById(16L);

        assertFalse(optionalAddressDTO.isPresent());

    }


}