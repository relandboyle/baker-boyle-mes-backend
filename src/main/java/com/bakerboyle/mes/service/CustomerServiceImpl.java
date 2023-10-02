package com.bakerboyle.mes.service;

import com.bakerboyle.mes.model.CustomerEntity;
import com.bakerboyle.mes.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerEntityService {

    private final CustomerRepository customerRepository;
    @Override
    public String createCustomerEntity(CustomerEntity customer) {
        return customerRepository
                .save(customer).getCustomerId();
    }

    @Override
    public Optional<CustomerEntity> findCustomerEntity(String customerId) {
        return customerRepository
                .findById(customerId);
    }

    @Override
    public CustomerEntity updateCustomerEntity(String customerId, CustomerEntity customer) {
        CustomerEntity theCustomerEntity = customerRepository.findById(customerId)
                .stream()
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException(
                                String.format("customer with id %s does not exist",
                                        customer.getCustomerId())));

        theCustomerEntity.setFirstName(customer.getFirstName());
        theCustomerEntity.setLastName(customer.getLastName());
        theCustomerEntity.setEmail(customer.getEmail());

        return customerRepository
                .save(theCustomerEntity);
    }

    @Override
    public void deleteCustomerEntity(String customerId) {
        CustomerEntity theCustomerEntity = customerRepository
                .findById(customerId).get();
        if (theCustomerEntity == null){
            throw new RuntimeException(
                    String.format("The customer with id %s does not exist",
                            customerId));
        }
        customerRepository
                .delete(theCustomerEntity);

    }
}