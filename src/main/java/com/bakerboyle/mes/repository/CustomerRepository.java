package com.bakerboyle.mes.repository;

import com.bakerboyle.mes.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByEmail(String email);

    @Query(value = "SELECT cust_id FROM customers", nativeQuery = true)
    List<String> getAllCustId();
}
