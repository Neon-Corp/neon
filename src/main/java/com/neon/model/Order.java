package com.neon.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="listing_order")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "listing_id")
    private Integer listingId;

    @Column(name = "buyer_id")
    private Integer buyerId;

    @Column(name = "order_date", columnDefinition="DATETIME DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp orderDate;

    @Column(name="total_value")
    private Double totalOrderValue;

    public Order(Integer listingId, Integer buyerId, Double totalOrderValue) {
        this.listingId = listingId;
        this.buyerId = buyerId;
        this.totalOrderValue = totalOrderValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getListingId() {
        return listingId;
    }

    public void setListingId(Integer listingId) {
        this.listingId = listingId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalOrderValue() {
        return totalOrderValue;
    }

    public void setTotalOrderValue(Double totalOrderValue) {
        this.totalOrderValue = totalOrderValue;
    }
}
