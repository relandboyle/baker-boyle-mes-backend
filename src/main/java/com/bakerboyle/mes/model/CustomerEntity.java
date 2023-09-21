package com.bakerboyle.mes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
//    @GeneratedValue
    @Column(name = "cust_id")
    private String customerId;

    @Column(name = "internal_id")
    private UUID internalId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;
}