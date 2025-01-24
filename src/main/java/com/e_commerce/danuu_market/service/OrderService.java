package com.e_commerce.danuu_market.service;

import com.e_commerce.danuu_market.models.Orders;
import com.e_commerce.danuu_market.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    //find single order
    public Orders findOrder(int id){
        return orderRepository.findById(id);
    }

    //find all orders
    public List<Orders> findAllOrders(int id){
        return findAllOrders(id);
    }
}
