package com.bakerboyle.mes.controller;

import com.bakerboyle.mes.model.CustomerEntity;
import com.bakerboyle.mes.service.CustomerEntityService;
import com.bakerboyle.mes.service.IdentifierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "http://localhost:4209")
@RequestMapping(value = "api/v1/customers")
public class CustomerController {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    IdentifierService identifierService;
    @Autowired
    CustomerEntityService customerEntityService;
    @GetMapping(path = "{custId}")
    public ResponseEntity<CustomerEntity> getCustomer(@PathVariable String custId) {
        ResponseEntity<CustomerEntity> response = null;

        try {
            Optional<CustomerEntity> customer = customerEntityService.findCustomerEntity(custId);
            if (customer.isPresent()) {
                response = new ResponseEntity<>(customer.get(), HttpStatus.OK);
                return response;
            }
            else {
                throw new ServiceException("Customer " + custId + " not found!");
            }
        }
        catch (NullPointerException e) {
            System.out.println("Customer not found! " + e.getMessage());
            throw e;
        }
    }

    @Query(value = "SELECT customer_id FROM customers", nativeQuery = true)
    public Collection<String> findAllCustomerIds() {
        return null;
    }

    @PostMapping()
    public ResponseEntity<CustomerEntity> createCustomer(@RequestBody CustomerEntity customer) {
        ResponseEntity<CustomerEntity> response = null;

        // TODO: Add a query to get a list of all existing customer ID's
        // create a list of one customer ID to eliminate when generating a new customer ID
        List<String> existing = Stream.of("CUST-123456").toList();

        String newCustomerId = identifierService.generateEntityId(existing, "CUST-");
        UUID newUUID = identifierService.generateUUIDFromInput(newCustomerId);
        customer.setCustomerId(newCustomerId);
        customer.setInternalId(newUUID);

        String confirmation = customerEntityService.createCustomerEntity(customer);
        Optional<CustomerEntity> newCustomer = customerEntityService.findCustomerEntity(confirmation);
        if (!confirmation.isEmpty() && newCustomer.isPresent()) {
            response = new ResponseEntity<>(newCustomer.get(), HttpStatus.OK);
        }

        return response;
    }

    @DeleteMapping(path = "{customerId}")
    public ResponseEntity<CustomerEntity> deleteCustomer(@PathVariable String customerId) {
        System.out.println("TEST");
        System.out.println(findAllCustomerIds());
        ResponseEntity<CustomerEntity> response = null;

        Optional<CustomerEntity> customerToDelete = customerEntityService.findCustomerEntity(customerId);
        if (customerToDelete.isPresent()) {
            customerEntityService.deleteCustomerEntity(customerId);
            response = new ResponseEntity<>(customerToDelete.get(), HttpStatus.OK);
        }

        return response;
    }
}
