package com.e_commerce.danuu_market.repository;

import com.e_commerce.danuu_market.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Orders, Integer> {

}
