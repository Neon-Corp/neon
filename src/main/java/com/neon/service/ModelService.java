package com.neon.service;

import com.neon.model.Model;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelService {
    private static List<Model> modelList = new ArrayList<>();

    public List<Model> findAll(String modelName) {
        Model model = new Model();
        model.setModelName("Ericcson");
        List<Model> modelList = new ArrayList<>();
        modelList.add(model);
        return modelList;
    }

    public static List<Model> getAll(){
        Model model = new Model();
        model.setId(0);
        model.setModelName("Ericcson");
        modelList.add(model);
        Model model2 = new Model();
        model2.setId(1);
        model2.setModelName("Nokia");
        modelList.add(model2);

        return modelList;
    }
}
