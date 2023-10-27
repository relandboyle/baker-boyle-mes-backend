package com.bakerboyle.mes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class Part {
    @Id
    @Column(name = "part_id", unique = true, updatable = false)
    private String partId;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String variant;
    @Column
    private String stockUnit;
    @Column
    private String stockUnitValue;
    @Column
    private String stockQuantity;
    @Column
    private String stockLocation;
    @Column
    private String restockLevel;
    @Column
    private String status;
}
