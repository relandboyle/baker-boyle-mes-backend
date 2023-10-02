package com.bakerboyle.mes.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class IdentifierService {

    public UUID generateUUIDFromInput(String input) {
        // generate a UUID from a client-facing ID
        return UUID.nameUUIDFromBytes(input.getBytes());
    }

    public UUID generateRandomUUID() {
        return UUID.randomUUID();
    }

    public String generateEntityId(List<String> existingEntities, String prefix) {
        // generate a list of just the numeric portion of all existing customer IDs
        List<Integer> existingNumbers = existingEntities.stream()
                .map(ent -> Integer.parseInt(ent.split(prefix)[1]))
                .toList();
        // generate an array of all possible ID numbers omitting existing customer IDs
        ArrayList<Integer> idPool = new ArrayList<>();
        for (int i = 100000; i < 1000000; i++) {
            if (!existingNumbers.contains(i)) {
                idPool.add(i);
            }
        }
        // randomly select an item from the pool and construct a customer ID string
        long randomIndex = Math.round(Math.floor(Math.random() * idPool.size()));
        int newEntityId = idPool.get((int)randomIndex);
        return String.valueOf(prefix + newEntityId);
    }
}
