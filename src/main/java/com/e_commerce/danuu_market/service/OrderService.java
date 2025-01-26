package com.e_commerce.danuu_market.service;

import com.e_commerce.danuu_market.Exceptions.NotFoundException;
import com.e_commerce.danuu_market.dto.CreateOrderRequest;
import com.e_commerce.danuu_market.dto.OrderProductRequest;
import com.e_commerce.danuu_market.models.OrderItem;
import com.e_commerce.danuu_market.models.Orders;
import com.e_commerce.danuu_market.models.Products;
import com.e_commerce.danuu_market.repository.OrderItemRepository;
import com.e_commerce.danuu_market.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductService productService;

    //find single order
    public Orders findOrder(int id){
        return orderRepository.findById(id);
    }

    //find all orders
    public List<Orders> findAllOrders(){
        return orderRepository.findAll();
    }

    @Transactional(rollbackFor = NotFoundException.class)
    public Orders createNewOrder(CreateOrderRequest createOrderRequest) throws NotFoundException {
        Orders order = new Orders();
        order.setTotal_price(createOrderRequest.getTotal_price());
        order.setQuantity(createOrderRequest.getQuantity());
        order.setStatus(createOrderRequest.getStatus());
        orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();
        for(OrderProductRequest productRequest : createOrderRequest.getProducts()){
            Products products = productService.findById(productRequest.getProductId());
            if(products == null){
                throw new NotFoundException("One of the product in the list does not exist");
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(products);
            orderItem.setQuantity(productRequest.getQuantity());
            orderItems.add(orderItem);
        }
        orderItemRepository.saveAll(orderItems);
        return order;

    }
}
