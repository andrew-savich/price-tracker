package com.andrewsavich.price_checker.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity
public class User {
    @Id
    private UUID id;
    private String email;
    private String username;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<TrackedProduct> products;
}
