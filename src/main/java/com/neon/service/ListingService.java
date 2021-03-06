package com.neon.service;

import com.neon.model.Listing;
import com.neon.repo.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public Iterable<Listing> findAllBySellerId(Integer sellerId) {
        return listingRepository.getListingsBySellerId(sellerId);
    }

    public void delete(Listing listing) {
        listingRepository.delete(listing);
    }

    public Iterable<Listing> getAllFromBrand(Integer brandID) {
        return listingRepository.findAllFromBrand(brandID);
    }

    public Iterable<Listing> getAllFromModel(Integer modelID){
        return listingRepository.findAllFromModel(modelID);
    }
}
