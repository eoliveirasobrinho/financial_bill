package com.itgen.financialit.adapters.out.persistence.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.itgen.financialit.domain.model.InvoicePayable;

public interface MongoInvoicePayableRepository extends MongoRepository<InvoicePayable,Long>{

}
