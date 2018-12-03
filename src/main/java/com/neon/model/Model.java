package com.neon.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Model implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "model")
    private String modelName;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
