package com.neon.model;

import javax.persistence.*;

@Entity
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "listing_id")
    private Integer listingId;

    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name = "buyer_id")
    private Integer buyerId;

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

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }
}
