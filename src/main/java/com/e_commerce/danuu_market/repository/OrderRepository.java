package com.e_commerce.danuu_market.repository;

import com.e_commerce.danuu_market.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
public Orders findById(int id);
public List<Orders> findAll();


}
