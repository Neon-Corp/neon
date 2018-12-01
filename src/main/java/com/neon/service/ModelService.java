package com.neon.service;

import com.neon.model.Model;
import com.neon.repo.ModelRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
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

    public Integer getIdByName(String modelName) {
        Iterable<Model> all = getAll();
        for (Model m : all){
            if (m.getModelName().toLowerCase().contains(modelName.toLowerCase())){
                return m.getId();
            }
        }
        return -1;
    }

    public String getNameById(Integer id){
        Iterable<Model> all  = getAll();
        for (Model m : all){
            if (m.getId() == id){
                return m.getModelName();
            }
        }
        return "";
    }
}
