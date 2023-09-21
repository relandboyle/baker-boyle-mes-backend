package com.bakerboyle.mes.repository;

import com.bakerboyle.mes.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

}
