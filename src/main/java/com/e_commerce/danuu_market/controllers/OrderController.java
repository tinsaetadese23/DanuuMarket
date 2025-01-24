package com.e_commerce.danuu_market.controllers;

import com.e_commerce.danuu_market.models.Orders;
import com.e_commerce.danuu_market.models.Products;
import com.e_commerce.danuu_market.repository.OrderRepository;
import com.e_commerce.danuu_market.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;
    Map<String, String> order_responses = new HashMap<>();

    @GetMapping("/getOrder")
    public ResponseEntity<?> findOrder(@RequestParam int id){
        Orders orders = orderService.findOrder(id);
        if(orders == null){
            order_responses.put("error","No order found with this ID");
            order_responses.put("error-code","-1");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(order_responses);
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/get")
    public ResponseEntity<?> findAllOrders(){
        List<Orders> orders = orderService.findAllOrders();
        if(orders.isEmpty()){
            order_responses.put("error","There is no order");
            order_responses.put("error_code","-1");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(order_responses);
        }

        return ResponseEntity.ok(orders);
    }

}
