package com.neon.controller;

import com.neon.model.AutocompleteBrand;
import com.neon.model.Brand;
import com.neon.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutocompleteBrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/brand/search")
    public ResponseEntity<?> getBrandSuggestions() {
        AutocompleteBrand autocompleteBrand = new AutocompleteBrand();
        for (Brand brand : brandService.getAll()) {
            autocompleteBrand.addSuggestion(brand.getId(), brand.getBrand());
        }
        brandService.getAll();
        return ResponseEntity.ok(autocompleteBrand);
    }
}
