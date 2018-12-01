package com.neon.controller;

import com.neon.model.AutocompleteBrand;
import com.neon.model.Model;
import com.neon.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutocompleteModelController {

    @Autowired
    private ModelService modelService;

    @PostMapping("/model/search")
    public ResponseEntity<?> getModelSuggestions(@RequestBody String suggestionBrandID) {
        Integer brandID = Integer.parseInt(suggestionBrandID.split("=")[1]);
        AutocompleteBrand autocompleteBrand = new AutocompleteBrand();
        for (Model model : modelService.getAllFromBrand(brandID)) {
            autocompleteBrand.addSuggestion(model.getId(), model.getModelName());
        }
        modelService.getAll();
        return ResponseEntity.ok(autocompleteBrand);
    }
}
