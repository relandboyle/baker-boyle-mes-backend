package com.bakerboyle.mes.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "customers")
public class CustomerEntity implements Serializable {
    @Id
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

    @CreationTimestamp
    @Column(name = "date_created")
    private Date dateCreated;

    @LastModifiedDate
    @Column(name = "date_modified")
    private Date dateModified;
}
