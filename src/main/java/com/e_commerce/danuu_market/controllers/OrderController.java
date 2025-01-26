package com.e_commerce.danuu_market.controllers;

import com.e_commerce.danuu_market.Exceptions.NotFoundException;
import com.e_commerce.danuu_market.dto.CreateOrderRequest;
import com.e_commerce.danuu_market.dto.OrderProductRequest;
import com.e_commerce.danuu_market.models.OrderItem;
import com.e_commerce.danuu_market.models.Orders;
import com.e_commerce.danuu_market.models.Products;
import com.e_commerce.danuu_market.repository.OrderItemRepository;
import com.e_commerce.danuu_market.service.OrderService;
import com.e_commerce.danuu_market.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderItemRepository orderItemRepository;
    Map<String, String> order_responses = new HashMap<>();

    Logger LOG = LoggerFactory.getLogger(OrderController.class);

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

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody CreateOrderRequest createOrderRequest) throws NotFoundException {
        try{
            orderService.createNewOrder(createOrderRequest);
        }catch(NotFoundException e){
            order_responses.put("error",e.getMessage());
            order_responses.put("error-code","-1");
            order_responses.put("success",null);
            LOG.info("the api call responded : "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(order_responses);
        }catch(Exception e){
            order_responses.put("error",e.getMessage());
            order_responses.put("error-code","-3");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(order_responses);
        }
        order_responses.put("success","New order resource has been created");
        order_responses.put("error",null);
        return ResponseEntity.status(HttpStatus.CREATED).body(order_responses);
    }


}
