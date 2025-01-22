package com.e_commerce.danuu_market.repository;

import com.e_commerce.danuu_market.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products, Integer> {
 public Products findProductsByName(String name);
 public List<Products> findAll();
 public Products findById(int id);
}
