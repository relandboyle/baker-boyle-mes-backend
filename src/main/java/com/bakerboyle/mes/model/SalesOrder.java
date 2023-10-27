package com.bakerboyle.mes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales_orders")
public class SalesOrder implements Serializable {

    @Id
    @Column(name = "sale_id", unique = true, updatable = false)
    private String salesOrderId;

//    @ToString.Exclude
//    @ManyToOne
    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "internal_id", unique = true, updatable = false)
    private UUID internalId;

    @Column(name = "status")
    private String status;

    @Column(name = "value")
    private String value;

    @Column(name = "sales_tax")
    private Long salesTax;

    @Column(name = "date_created", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant dateCreated;
}
