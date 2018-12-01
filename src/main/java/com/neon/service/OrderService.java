package com.neon.service;

import com.neon.model.Order;
import com.neon.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    public List<Order> findByBuyerId(Integer buyerId){
        //orderRepository.
        return null;
    }
}
