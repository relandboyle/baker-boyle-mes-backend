package com.bakerboyle.mes.service;

import com.bakerboyle.mes.model.CustomerEntity;

import java.util.List;
import java.util.Optional;


public interface CustomerService {
    List<CustomerEntity> getAllCustomers();
    String createCustomerEntity(CustomerEntity customer);
    Optional<CustomerEntity> findCustomerEntity(String customerId);
    CustomerEntity updateCustomerEntity(String customerId, CustomerEntity customer);
    void deleteCustomerEntity(String customerId);
}
