package com.dauphine.blogger.model;

import java.util.UUID;

public class Category {
    private UUID id;
    private String name;

    // Constructeur nécessaire pour ton contrôleur
    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters et Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}