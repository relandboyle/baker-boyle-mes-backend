package com.bakerboyle.mes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @Column(name = "cust_id", unique = true, updatable = false)
    private String customerId;

    @Column(name = "internal_id", unique = true, updatable = false)
    private UUID internalId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "date_created", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant dateCreated;

    @Column(name = "date_modified")
    @LastModifiedDate
    private Instant dateModified;
}
