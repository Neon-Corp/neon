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

    public Iterable<Brand> getAll() {
        return brandRepository.findAll();
    }

    public Brand getBrandById(Integer brandId){
        Optional<Brand> brand = brandRepository.findById(brandId);
        if (brand.isPresent()){
            return brand.get();
        } else {
            return null;
        }
    }
}
