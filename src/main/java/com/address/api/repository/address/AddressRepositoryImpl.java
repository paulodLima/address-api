package com.address.api.repository.address;

import com.address.api.model.AddressEntity;
import com.address.api.model.filter.AddressFilter;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.address.api.util.RepositoryUtils.*;

public class AddressRepositoryImpl implements AddressQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AddressEntity> findAllByFilter(AddressFilter addressFilter, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AddressEntity> criteriaQuery = builder.createQuery(AddressEntity.class);
        Root<AddressEntity> rootAddress = criteriaQuery.from(AddressEntity.class);

        Predicate[] predicates = createAddressPredicates(builder,addressFilter,rootAddress);

        if(predicatesExists(predicates)){
            criteriaQuery.where(predicates);
        }

        addOrdination(pageable, builder, criteriaQuery, rootAddress);
        TypedQuery<AddressEntity> query = entityManager.createQuery(criteriaQuery);
        addPagination(query, pageable);

        List<AddressEntity> resultList = query.getResultList();

        return new PageImpl<>(resultList).getContent();
    }

    private Predicate[] createAddressPredicates(CriteriaBuilder builder, AddressFilter addressFilter, Root<AddressEntity> rootAddress) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.isNull(addressFilter)){
            return null;
        }
        if (StringUtils.isNotBlank(addressFilter.getZipCode())) {
            predicates.add(builder.like(builder.lower(rootAddress.get("zipCode")), addressFilter.getZipCode().toLowerCase() + "%"));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

}
