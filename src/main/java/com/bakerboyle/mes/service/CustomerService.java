package com.bakerboyle.mes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class CustomerService {
    @Autowired
    RestTemplate restTemplate;
}
