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

    public Model findOneById(Integer modelID) {
        return modelRepository.findById(modelID).get();
    }

    public Iterable<Model> getAll() {
        return modelRepository.findAll();
    }

    public Iterable<Model> getAllFromBrand(Integer brandID){
        return modelRepository.findAllFromBrand(brandID);
    }

    public Integer getIdByName(String modelName) {
        Iterable<Model> all = getAll();
        for (Model m : all){
            if (m.getModelName().toLowerCase().equals(modelName.toLowerCase())){
                return m.getId();
            }
        }
        return -1;
    }

    public String getNameById(Integer id){
        Iterable<Model> all  = getAll();
        for (Model m : all){
            if (m.getId().equals(id)){
                return m.getModelName();
            }
        }
        return "";
    }
}
