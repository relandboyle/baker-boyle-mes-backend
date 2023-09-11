package com.bakerboyle.mes.controller;

import com.bakerboyle.mes.model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4209")
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(path = "/{userId}")
    public ResponseEntity<String> getUser(@RequestHeader Map<String, String> reqHeaders, @PathVariable String userId) {
        System.out.println("REQUEST HEADERS: " + reqHeaders);
        System.out.println("REQUEST PARAM: " + userId);
        ResponseEntity<String> response = new ResponseEntity<>("Hello World", HttpStatus.OK);
        return response;
    }

    @PostMapping()
    public User createUser(@RequestHeader Map<String, String> reqHeaders, @RequestBody User body) {
        System.out.println("REQUEST HEADERS: " + reqHeaders);
        System.out.println("REQUEST BODY: " + body);

        User user = new User();

        ResponseEntity<User> response = new ResponseEntity<>(body, HttpStatus.OK);
        return response.getBody();
    }
}
