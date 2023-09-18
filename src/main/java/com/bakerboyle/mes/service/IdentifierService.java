package com.bakerboyle.mes.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class IdentifierService {

    public UUID generateUUIDFromInput(String input) {
        return UUID.nameUUIDFromBytes(input.getBytes());
    }

    public UUID generateRandomUUID() {
        return UUID.randomUUID();
    }

    public String generateEntityId(List<String> entityList, String prefix) {
        List<Integer> existingNumbers = entityList.stream()
                .map(ent -> Integer.parseInt(ent.split(prefix)[1]))
                .toList();

        ArrayList<Integer> idPool = new ArrayList<>();
        for (int i = 100000; i < 1000000; i++) {
            if (!existingNumbers.contains(i)) {
                idPool.add(i);
            }
        }

        long randomIndex = Math.round(Math.floor(Math.random() * idPool.size()));
        int newEntityId = idPool.get((int)randomIndex);
        return prefix + newEntityId;
    }
}
