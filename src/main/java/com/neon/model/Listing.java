package com.neon.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Listing {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "model_id")
    private Integer modelId;

    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name = "condition_id")
    private Integer conditionId;

    @Column(name = "listed_on", columnDefinition="DATETIME DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp listedDate;

    private String description;

    private double price;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getConditionId() {
        return conditionId;
    }

    public void setConditionId(Integer conditionId) {
        this.conditionId = conditionId;
    }

    public Timestamp getListedDate() {
        return listedDate;
    }

    public void setListedDate(Timestamp listedDate) {
        this.listedDate = listedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}