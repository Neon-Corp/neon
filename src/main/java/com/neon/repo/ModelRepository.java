package com.neon.repo;

import com.neon.model.Model;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends CrudRepository<Model, Integer> {

    @Query("SELECT m FROM Model m JOIN m.brand b WHERE b.id = :brandID")
    Iterable<Model> findAllFromBrand(@Param("brandID") Integer brandID);
}
