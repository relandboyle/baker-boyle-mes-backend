package com.bakerboyle.mes.service;

import com.bakerboyle.mes.model.Customer;
import com.bakerboyle.mes.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<String> getAllCustId() {
        return customerRepository.getAllCustId();
    }

    @Override
    public String createCustomerEntity(Customer customer) {
        return customerRepository
                .save(customer).getCustomerId();
    }

    @Override
    public Optional<Customer> findCustomerEntity(String customerId) {
        return customerRepository
                .findById(customerId);
    }

    @Override
    public Customer updateCustomerEntity(String customerId, Customer customer) {
        Customer theCustomer = customerRepository.findById(customerId)
                .stream()
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException(
                                String.format("customer with id %s does not exist",
                                        customer.getCustomerId())));
        System.out.println("TEST: " + !customer.getCustomerId().isBlank());
        theCustomer.setFirstName(customer.getFirstName());
        theCustomer.setLastName(customer.getLastName());
        theCustomer.setEmail(customer.getEmail());

        return customerRepository
                .save(theCustomer);
    }

    @Override
    public void deleteCustomerEntity(String customerId) {
        Customer theCustomer = customerRepository
                .findById(customerId).get();
        if (theCustomer == null) {
            throw new RuntimeException(
                    String.format("The customer with id %s does not exist",
                            customerId));
        }
        customerRepository
                .delete(theCustomer);

    }
}