package com.itgen.financialit.adapters.out.persistence.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itgen.financialit.adapters.out.persistence.entity.SupplierEntity;

@Repository
public interface JpaSupplierRepository extends JpaRepository<SupplierEntity, Long>{

}
