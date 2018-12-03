package com.neon.repo;

import com.neon.model.Listing;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends CrudRepository<Listing, Integer> {

    @Query("SELECT l FROM Listing l WHERE l.seller.id = :sellerId")
    Iterable<Listing> getListingsBySellerId(@Param("sellerId") Integer sellerId);

    @Query("SELECT l FROM Listing l JOIN l.model m JOIN m.brand b WHERE l.status = 1 AND b.id = :brandID")
    Iterable<Listing> findAllFromBrand(@Param("brandID") Integer brandID);

    @Query("SELECT l FROM Listing l JOIN l.model m WHERE l.status = 1 AND m.id = :modelID")
    Iterable<Listing> findAllFromModel(@Param("modelID") Integer modelID);
}

