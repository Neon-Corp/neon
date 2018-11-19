package com.neon.service;

import com.neon.model.Brand;
import com.neon.repo.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelService modelService;

    public Iterable<Brand> getAll() {
        return brandRepository.findAll();
    }
}
