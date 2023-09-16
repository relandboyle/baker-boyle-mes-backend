package com.bakerboyle.mes.controller;

import com.bakerboyle.mes.model.Customer;
import com.bakerboyle.mes.service.IdentifierService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4209")
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    IdentifierService generateId;
    @GetMapping(path = "/{custId}")
    public ResponseEntity<String> getCustomer(@RequestHeader HttpHeaders reqHeaders, @PathVariable String custId) {

        Customer staticTestUser = new Customer();
        staticTestUser.setFirstName("Elon");
        staticTestUser.setLastName("Musk");



        System.out.println("REQUEST HEADERS: " + reqHeaders);
        System.out.println("REQUEST PARAM: " + custId);
        ResponseEntity<String> response = new ResponseEntity<>("Hello World", HttpStatus.OK);
        return response;
    }

    @PostMapping()
    public Customer createCustomer(@RequestHeader HttpHeaders reqHeaders, @RequestBody Customer body) {
        System.out.println("REQUEST HEADERS: " + reqHeaders);
        System.out.println("REQUEST BODY: " + body);

        UUID custId = generateId.generateIdFromInput("CUST-123456");
        body.setCustomerId(custId.toString());

        ResponseEntity<Customer> response = new ResponseEntity<>(body, HttpStatus.OK);
        System.out.println("RESPONSE: " + response.getBody());
        return response.getBody();
    }
}
