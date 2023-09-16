package com.bakerboyle.mes.service;

import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class IdentifierService {
    public UUID generateIdFromInput(String input) {
        return UUID.nameUUIDFromBytes(input.getBytes());
    }
    public UUID generateRandomId() {
        return UUID.randomUUID();
    }
}
