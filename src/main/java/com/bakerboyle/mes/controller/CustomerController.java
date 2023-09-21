package com.bakerboyle.mes.controller;

import com.bakerboyle.mes.model.CustomerEntity;
import com.bakerboyle.mes.service.CustomerEntityService;
import com.bakerboyle.mes.service.IdentifierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CustomerEntity> getCustomer(@RequestHeader HttpHeaders reqHeaders, @PathVariable String custId) {
        System.out.println("REQUEST HEADERS: " + reqHeaders);
        System.out.println("REQUEST PARAM: " + custId);

        Optional<CustomerEntity> customer = customerEntityService.findCustomerEntity(custId);
        System.out.println("CUSTOMER: " + customer.isPresent());
        ResponseEntity<CustomerEntity> response = null;
        if (customer.isPresent()) {
            response = new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }
        System.out.println("RESPONSE: " + response.getBody());
        return response;
    }

    @PostMapping()
    public ResponseEntity<CustomerEntity> createCustomer(@RequestHeader HttpHeaders reqHeaders, @RequestBody CustomerEntity customer) {
        List<String> existing = Stream.of("CUST-123456").toList();
        String newCustomerId = identifierService.generateEntityId(existing, "CUST-");
        UUID newUUID = identifierService.generateUUIDFromInput(newCustomerId);
        customer.setCustomerId(newCustomerId);
        customer.setInternalId(newUUID);

        String customerId = customerEntityService.createCustomerEntity(customer);
        Optional<CustomerEntity> confirmation = customerEntityService.findCustomerEntity(newCustomerId);

        ResponseEntity<CustomerEntity> response = new ResponseEntity<>(confirmation.get(), HttpStatus.OK);
        System.out.println("RESPONSE: " + response.getBody());
        return response;
    }

    @DeleteMapping(path = "{custId}")
    public ResponseEntity<CustomerEntity> deleteCustomer(@PathVariable String custId) {
        Optional<CustomerEntity> customer = customerEntityService.findCustomerEntity(custId);
        System.out.println("CUSTOMER: " + customer.isPresent());
        if (customer.isPresent()) {
            customerEntityService.deleteCustomerEntity(custId);
        }
        Optional<CustomerEntity> confirmation = customerEntityService.findCustomerEntity(custId);
        System.out.println("CONFIRMATION: " + confirmation.isPresent());
        if (confirmation.isEmpty()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        }
    }
}
