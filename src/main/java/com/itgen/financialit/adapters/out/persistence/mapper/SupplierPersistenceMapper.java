package com.itgen.financialit.adapters.out.persistence.mapper;

import org.mapstruct.Mapper;

import com.itgen.financialit.adapters.out.persistence.entity.SupplierEntity;
import com.itgen.financialit.domain.model.Supplier;

@Mapper(componentModel = "spring")
public interface SupplierPersistenceMapper {
    SupplierEntity toEntity(Supplier domain);
    Supplier toDomain(SupplierEntity entity);
}
