package com.neon.service;

import com.neon.model.Listing;
import com.neon.model.Order;
import com.neon.model.User;
import com.neon.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ListingService listingService;

    public Order saveOrder(Integer listingID, User buyer){
        Listing listing = listingService.findOne(listingID).get();
        listing.setStatus(0);
        listingService.save(listing);
        Order order= new Order(listing, buyer, listing.getPrice());
        return orderRepository.save(order);
    }

    public Iterable<Order> findAllByBuyerId(Integer buyerId){
        return orderRepository.getOrderByBuyer(buyerId);
    }
}
