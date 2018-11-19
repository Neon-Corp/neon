package com.neon.service;

import com.neon.model.Condition;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ConditionService {
    private static List<Condition> conditionList = new ArrayList<Condition>();

    public static List<Condition> getAll() {
        Condition condition = new Condition(0, "Ruim", "Bem usado");
        conditionList.add(condition);
        return conditionList;
    }
}
