package com.neon.service;

import com.neon.model.Listing;
import com.neon.repo.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ListingService {

    @Autowired
    private ListingRepository listingRepository;

    public Optional<Listing> findOne(Integer id) {
        return listingRepository.findById(id);
    }

    public Listing save(Listing entity){
        return listingRepository.save(entity);
    }

    public Iterable<Listing> findAll(){
        return listingRepository.findAll();
    }

    public void delete(Listing listing) {
        listingRepository.delete(listing);
    }
}
