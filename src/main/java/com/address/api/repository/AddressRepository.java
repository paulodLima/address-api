package com.address.api.repository;

import com.address.api.model.AddressEntity;
import com.address.api.repository.address.AddressQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long>, AddressQuery {
}
