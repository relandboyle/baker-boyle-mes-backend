package com.bakerboyle.mes.controller;

import com.bakerboyle.mes.model.Customer;
import com.bakerboyle.mes.service.CustomerService;
import com.bakerboyle.mes.service.IdentifierService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4209")
@RequestMapping(value = "api/v1/customers")
public class CustomerController {
    @Autowired
    IdentifierService identifierService;
    @Autowired
    CustomerService customerService;

    @GetMapping(path = "allIds")
    public List<String> getAllCustomerIds() throws ServiceException {
        return customerService.getAllCustomerIdOnly();
    }

    @GetMapping(path = "{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String customerId) throws ServiceException {
        System.out.println("CUSTOMER ID: " + customerId);
        Optional<Customer> customer = customerService.findCustomer(customerId);
        ResponseEntity<Customer> response = null;
        if (customer.isPresent()) {
            response = new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }
        return response;
    }

    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws ServiceException {
        ResponseEntity<Customer> response = null;
        List<String> existingIds = getAllCustomerIds();

        String newCustomerId = identifierService.generateEntityId(existingIds, "CUST-");
        UUID newUUID = identifierService.generateUUIDFromInput(newCustomerId);
        customer.setCustomerId(newCustomerId);
        customer.setInternalId(newUUID);

        String confirmation = customerService.createCustomer(customer);
        Optional<Customer> newCustomer = customerService.findCustomer(confirmation);
        if (!confirmation.isEmpty() && newCustomer.isPresent()) {
            response = new ResponseEntity<>(newCustomer.get(), HttpStatus.OK);
        }
        return response;
    }

    @PutMapping(path = "{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String customerId, @RequestBody Customer customer) throws ServiceException {
        Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping(path = "{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String customerId) throws ServiceException {
        System.out.println("TEST");
        ResponseEntity<Customer> response = null;

        Optional<Customer> customerToDelete = customerService.findCustomer(customerId);
        if (customerToDelete.isPresent()) {
            customerService.deleteCustomer(customerId);
            response = new ResponseEntity<>(customerToDelete.get(), HttpStatus.OK);
        }
        return response;
    }
}
