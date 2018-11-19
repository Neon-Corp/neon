package com.neon.repo;

import com.neon.model.Condition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends CrudRepository<Condition, Integer> {
}
