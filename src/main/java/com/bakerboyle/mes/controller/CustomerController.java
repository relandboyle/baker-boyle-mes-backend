package com.bakerboyle.mes.controller;

import com.bakerboyle.mes.model.CustomerEntity;
import com.bakerboyle.mes.service.CustomerService;
import com.bakerboyle.mes.service.IdentifierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    CustomerService customerService;

    @GetMapping(path = "allIds")
    public List<String> getAllCustomerIds() {
        List<CustomerEntity> allCustomers = customerService.getAllCustomers();
        ArrayList<String> allCustomerIds = new ArrayList<>();

        for (CustomerEntity cust : allCustomers) {
            allCustomerIds.add(cust.getCustomerId());
        }

        return allCustomerIds;
    }

    @GetMapping(path = "{custId}")
    public ResponseEntity<CustomerEntity> getCustomer(@PathVariable String customerId) {
        ResponseEntity<CustomerEntity> response = null;

        try {
            Optional<CustomerEntity> customer = customerService.findCustomerEntity(customerId);
            if (customer.isPresent()) {
                response = new ResponseEntity<>(customer.get(), HttpStatus.OK);
                return response;
            }
            else {
                throw new ServiceException("Customer " + customerId + " not found!");
            }
        }
        catch (NullPointerException e) {
            System.out.println("Customer not found! " + e.getMessage());
            throw e;
        }
    }

    @PostMapping()
    public ResponseEntity<CustomerEntity> createCustomer(@RequestBody CustomerEntity customer) {
        ResponseEntity<CustomerEntity> response = null;
        List<String> existingIds = getAllCustomerIds();

        String newCustomerId = identifierService.generateEntityId(existingIds, "CUST-");
        UUID newUUID = identifierService.generateUUIDFromInput(newCustomerId);
        customer.setCustomerId(newCustomerId);
        customer.setInternalId(newUUID);

        String confirmation = customerService.createCustomerEntity(customer);
        Optional<CustomerEntity> newCustomer = customerService.findCustomerEntity(confirmation);
        if (!confirmation.isEmpty() && newCustomer.isPresent()) {
            response = new ResponseEntity<>(newCustomer.get(), HttpStatus.OK);
        }

        return response;
    }

    @PutMapping(path = "{customerId}")
    public ResponseEntity<CustomerEntity> updateCustomer(@PathVariable String customerId, @RequestBody CustomerEntity customer) {
        CustomerEntity updatedCustomer = customerService.updateCustomerEntity(customerId, customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping(path = "{customerId}")
    public ResponseEntity<CustomerEntity> deleteCustomer(@PathVariable String customerId) {
        System.out.println("TEST");
        ResponseEntity<CustomerEntity> response = null;

        Optional<CustomerEntity> customerToDelete = customerService.findCustomerEntity(customerId);
        if (customerToDelete.isPresent()) {
            customerService.deleteCustomerEntity(customerId);
            response = new ResponseEntity<>(customerToDelete.get(), HttpStatus.OK);
        }

        return response;
    }
}
