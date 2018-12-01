package com.neon.service;

import com.neon.model.Brand;
import com.neon.repo.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelService modelService;

    public Iterable<Brand> getAll() {
        return brandRepository.findAll();
    }

    public String getBrandById(Integer brandId){
        Optional<Brand> brand = brandRepository.findById(brandId);
        if (brand.isPresent()){
            Brand brand1 = brand.get();
            return brand1.getBrand();
        } else {
            return "";
        }
    }
}
