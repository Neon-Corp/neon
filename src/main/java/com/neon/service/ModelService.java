package com.neon.service;

import com.neon.model.Model;
import com.neon.repo.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    public Iterable<Model> getAll() {
        return modelRepository.findAll();
    }

    public List<Model> getAllFromBrand(Integer brandId){
        Iterable<Model> fullList = getAll();
        List<Model> resultList = new ArrayList<>();
        for (Model m : fullList){
            if (m.getBrandId() == brandId){
                resultList.add(m);
            }
        }
        return resultList;
    }
}
