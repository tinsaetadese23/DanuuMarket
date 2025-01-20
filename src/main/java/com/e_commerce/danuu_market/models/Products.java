package com.e_commerce.danuu_market.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Nonnull
    private String name;

    @Nonnull
    private String description;

    @Nonnull
    private double price;

    @Nonnull
    private String category;

    @Nonnull
    private int stockQuantity;
}
