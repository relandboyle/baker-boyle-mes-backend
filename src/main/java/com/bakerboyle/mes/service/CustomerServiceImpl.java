package com.bakerboyle.mes.service;

import com.bakerboyle.mes.model.CustomerEntity;
import com.bakerboyle.mes.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerEntityService {

    private final CustomerRepository customerRepository;
    @Override
    public Integer createCustomerEntity(CustomerEntity customer) {
        return customerRepository
                .save(customer).getCustomerId();
    }

    @Override
    public Optional<CustomerEntity> findCustomerEntity(Integer customerId) {
        return customerRepository
                .findById(customerId);

    }

    @Override
    public CustomerEntity updateCustomerEntity(Integer customerId, CustomerEntity customer) {
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
    public void deleteCustomerEntity(Integer customerId) {
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