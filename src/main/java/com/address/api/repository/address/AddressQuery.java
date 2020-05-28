package com.address.api.repository.address;

import com.address.api.model.AddressEntity;
import com.address.api.model.filter.AddressFilter;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AddressQuery {
    List<AddressEntity> findAllByFilter(AddressFilter addressFilter, Pageable pageable);
}
