package com.address.api.service;

import com.address.api.exception.MessageErrorImpl;
import com.address.api.model.AddressEntity;
import com.address.api.model.filter.AddressFilter;
import com.address.api.repository.AddressRepository;
import com.address.api.dto.AddressDTO;
import com.address.api.mapper.AddressMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import javax.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Objects;

import static com.address.api.util.MessagesUtil.getMessage;

@Service
public class AddressService {

    private final AddressMapper mapper;

    private final AddressRepository repository;

    public AddressService(AddressMapper mapper, AddressRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public AddressDTO processAddress(AddressDTO addressDTO) {
        if (Objects.isNull(addressDTO)) {
            return null;
        }
        return mapper.toAddressDTO(repository.save(mapper.toAddressEntity(addressDTO)));
    }

    public AddressDTO getAddressDTO(Long idAddress) {

         return mapper.toAddressDTO(repository.findById(idAddress).orElseThrow(() -> new EntityNotFoundException(getMessage(MessageErrorImpl.RESOURCE_NOT_FOUND, getMessage(MessageErrorImpl.ADDRESS)))));
    }

    public List<AddressEntity> getAddressAll(AddressFilter addressFilter, Pageable pageable) {

        return repository.findAllByFilter(addressFilter,pageable);

    }
  }
