package com.neon.service;

import com.neon.model.Condition;
import com.neon.repo.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConditionService {
    @Autowired
    private ConditionRepository conditionRepository;

    public Iterable<Condition> getAll() {
        return conditionRepository.findAll();
    }
}
