package com.bakerboyle.mes.service;

import com.bakerboyle.mes.model.CustomerEntity;

import java.util.Optional;


public interface CustomerEntityService {
    Integer createCustomerEntity(CustomerEntity customer);
    Optional<CustomerEntity> findCustomerEntity(Integer customerId);
    CustomerEntity updateCustomerEntity(Integer customerId, CustomerEntity customer);
    void deleteCustomerEntity(Integer customerId);
}
