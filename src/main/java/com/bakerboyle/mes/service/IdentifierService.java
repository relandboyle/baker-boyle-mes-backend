package com.bakerboyle.mes.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class IdentifierService {
    public UUID generateIdFromInput(String input) {
        return UUID.nameUUIDFromBytes(input.getBytes());
    }
    public UUID generateRandomId() {
        return UUID.randomUUID();
    }
    public String generateEntityId() {
        // temp argument placeholders
        String tempPrefix = "CUST-";
        List<String> tempExisting = new ArrayList<>();
        tempExisting.add("CUST-123456");
        tempExisting.add("CUST-451583");
        tempExisting.add("CUST-752579");
        tempExisting.add("CUST-321654");
        tempExisting.add("CUST-227881");
        tempExisting.add("CUST-402655");

        // generate an array of 6-digit numbers from existing customer numbers
        List<Integer> existingNumbers = tempExisting.stream()
                .map(ent -> {
                    String entNum = ent.split("-")[1];
                    return Integer.parseInt(entNum);
                })
                .toList();

        // generate an array of all 6-digit ints not already used in entity ID
        ArrayList<Integer> idPool = new ArrayList<>();
        for (int i = 100000; i < 1000000; i++) {
            if (!existingNumbers.contains(i)) {
                idPool.add(i);
            }
        }

        // generate a new entity ID from a randomly selected value
        long randomIndex = Math.round(Math.floor(Math.random() * idPool.size()));
        int newEntityId = idPool.get((int)randomIndex);
        return tempPrefix + newEntityId;
    }
}
