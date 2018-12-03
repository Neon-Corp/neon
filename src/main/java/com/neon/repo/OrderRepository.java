package com.neon.repo;

import com.neon.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Query("SELECT o FROM Order o JOIN o.listing l JOIN o.buyer u WHERE u.id = :buyerID")
    Iterable<Order> getOrderByBuyer(@Param("buyerID") Integer buyerID);

}
