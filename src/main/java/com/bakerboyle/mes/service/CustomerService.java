package com.bakerboyle.mes.service;

import com.bakerboyle.mes.model.Customer;

import java.util.List;
import java.util.Optional;


public interface CustomerService {
    List<Customer> getAllCustomers();
    List<String> getAllCustId();
    String createCustomerEntity(Customer customer);
    Optional<Customer> findCustomerEntity(String customerId);
    Customer updateCustomerEntity(String customerId, Customer customer);
    void deleteCustomerEntity(String customerId);
}
