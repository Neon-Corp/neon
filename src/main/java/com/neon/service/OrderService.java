package com.neon.service;

import com.neon.model.Listing;
import com.neon.model.Order;
import com.neon.model.User;
import com.neon.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ListingService listingService;

    public Order saveOrder(Integer listingID, Integer buyerID){
        Listing listing = listingService.findOne(listingID).get();
        listing.setStatus(0);
        listingService.save(listing);
        Order order= new Order(listingID, buyerID, listing.getPrice());
        return orderRepository.save(order);
    }

    public List<Order> findByBuyerId(Integer buyerId){
        //orderRepository.
        return null;
    }
}
