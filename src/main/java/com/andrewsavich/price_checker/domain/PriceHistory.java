package com.andrewsavich.price_checker.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class PriceHistory {
    @Id
    private UUID id;
    @ManyToOne
    private TrackedProduct product;
    private BigDecimal price;
    private LocalDateTime checkedAt;
}
