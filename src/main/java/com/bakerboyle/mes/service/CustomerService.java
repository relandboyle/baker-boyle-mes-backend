package com.bakerboyle.mes.service;

import com.bakerboyle.mes.model.Customer;

import java.util.List;
import java.util.Optional;


public interface CustomerService {
    List<Customer> getAllCustomers();
    List<String> getAllCustomerIdOnly();
    String createCustomer(Customer customer);
    Optional<Customer> findCustomer(String customerId);
    Customer updateCustomer(String customerId, Customer customer);
    void deleteCustomer(String customerId);
}
