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

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4209")
@RequestMapping(value = "api/v1/customers")
public class CustomerController {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    IdentifierService generateId;
    @Autowired
    CustomerEntityService customerEntityService;
    @GetMapping(path = "{custId}")
    public Optional<CustomerEntity> getCustomer(@RequestHeader HttpHeaders reqHeaders, @PathVariable String custId) {
        System.out.println("REQUEST HEADERS: " + reqHeaders);
        System.out.println("REQUEST PARAM: " + custId);

        Optional<CustomerEntity> response = customerEntityService.findCustomerEntity(Integer.parseInt(custId));
        return response;
    }

    @PostMapping()
    public ResponseEntity<Integer> createCustomer(@RequestHeader HttpHeaders reqHeaders, @RequestBody CustomerEntity body) {
        System.out.println("REQUEST HEADERS: " + reqHeaders);
        System.out.println("REQUEST BODY: " + body);

        Integer customerId = customerEntityService.createCustomerEntity(body);

        ResponseEntity<CustomerEntity> response = new ResponseEntity<>(body, HttpStatus.OK);
        System.out.println("RESPONSE: " + response.getBody());
//        return response.getBody();

        return new ResponseEntity<>(customerId, HttpStatus.CREATED);
    }
}
