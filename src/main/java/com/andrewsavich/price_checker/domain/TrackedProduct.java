package com.andrewsavich.price_checker.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
public class TrackedProduct {
    @Id
    private UUID id;
    private String productUrl;
    private String title;
    private BigDecimal currentPrice;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "product")
    List<PriceHistory> history;
}
