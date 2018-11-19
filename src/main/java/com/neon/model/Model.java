package com.neon.model;

import javax.persistence.*;

@Entity
public class Model {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "model")
    private String modelName;

    @Column(name = "brand_id")
    private Integer brandId;

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

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
}
