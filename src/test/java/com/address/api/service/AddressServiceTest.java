package com.address.api.service;

import com.address.api.configuration.MessageSourceConfiguration;
import com.address.api.dto.AddressDTO;
import com.address.api.exception.MessageErrorImpl;
import com.address.api.mapper.AddressMapper;
import com.address.api.model.AddressEntity;
import com.address.api.model.filter.AddressFilter;
import com.address.api.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import javax.mail.Address;
import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.parser.Entity;
import java.util.*;

import static com.address.api.util.MessagesUtil.getMessage;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {MessageSourceConfiguration.class})
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressMapper mapper;

    @Mock
    private AddressRepository repository;

    @Test
    void processAddress() {
        AddressDTO addressDTO = spy(AddressDTO.class);
        AddressEntity addressEntity = spy(AddressEntity.class);

        when(mapper.toAddressEntity(any(AddressDTO.class))).thenReturn(addressEntity);
        when(repository.save(any(AddressEntity.class))).thenReturn(addressEntity);

        addressService.processAddress(addressDTO);

        verify(mapper, times(1)).toAddressEntity(addressDTO);
        verify(repository, times(1)).save(addressEntity);
        verify(mapper, times(1)).toAddressDTO(addressEntity);
    }

    @Test
    void getAddressDTO() {
        AddressDTO addressDTO = spy(AddressDTO.class);
        AddressEntity addressEntity = mapper.toAddressEntity(addressDTO);

        when(repository.findById(anyLong())).thenReturn(Optional.of(new AddressEntity()));

        AddressDTO responseDTO = addressService.getAddressDTO(2L);

        verify(repository, times(1)).findById(2L);

        assertEquals(responseDTO, addressEntity);
    }

    @Test
    void getAddressDTONotFound() {

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class, () -> addressService.getAddressDTO(212L));

        verify(repository, times(1)).findById(212L);

        assertEquals(getMessage(MessageErrorImpl.RESOURCE_NOT_FOUND, getMessage(MessageErrorImpl.ADDRESS)), entityNotFoundException.getMessage());
    }

    @Test
    void getAddressAll() {
        AddressFilter addressFilter = spy(AddressFilter.class);
        
        AddressEntity addressEntity = spy(AddressEntity.class);
        addressEntity.setId(256L);
        
        AddressEntity secondEntity = spy(AddressEntity.class);
        addressEntity.setId(526L);

        List<AddressEntity> listAddress = Arrays.asList(addressEntity, secondEntity);

        when(repository.findAllByFilter(any(AddressFilter.class), any(Pageable.class))).thenReturn(listAddress);

        addressService.getAddressAll(addressFilter, Pageable.unpaged());

        verify(repository, times(1)).findAllByFilter(addressFilter, Pageable.unpaged());

    }

}