package com.neon.service;

import com.neon.model.Model;
import com.neon.repo.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public Iterable<Model> getAll() {
        return modelRepository.findAll();
    }
}
