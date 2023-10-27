package com.bakerboyle.mes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.util.List;

public class Assembly {
    @Id
    @Column(name = "part_id", unique = true, updatable = false)
    private String partId;
    @Column(name = "parts_list")
    private List<String> partsList;
    @Column(name = "name")
    String name;
    @Column(name = "description")
    String description;
    @Column(name = "variant")
    String variant;
    @Column(name = "value")
    String value;
    @Column(name = "effort")
    String effort;
    @Column(name = "on_hand")
    String onHand;
    @Column(name = "available_quantity")
    String availableQuantity;
    @Column(name = "status")
    String status;
}
