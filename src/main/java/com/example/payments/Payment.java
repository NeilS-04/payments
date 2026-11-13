package com.example.payments;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String merchantRef;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String status;

    private Instant createdAt = Instant.now();

    protected Payment() { }  // required by JPA

    public Payment(String merchantRef, BigDecimal amount) {
        this.merchantRef = merchantRef;
        this.amount = amount;
        this.status = "CREATED";
    }

    public Long getId() { return id; }
    public String getMerchantRef() { return merchantRef; }
    public BigDecimal getAmount() { return amount; }
    public String getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
}