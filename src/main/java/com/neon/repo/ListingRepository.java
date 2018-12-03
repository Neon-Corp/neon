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
}

