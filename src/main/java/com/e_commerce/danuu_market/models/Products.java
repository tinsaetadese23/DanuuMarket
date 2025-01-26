package com.e_commerce.danuu_market.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
