package com.neon.service;

import com.neon.model.Listing;
import com.neon.model.User;
import com.neon.repo.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListingService {

    @Autowired
    private ListingRepository listingRepository;

    private static int idCount = 0;

    private static List<Listing> listingList = new ArrayList<>();

    public static Listing findOne(Integer id) {
        for (Listing listing : listingList){
            if (listing.getId() == id){
                return listing;
            }
        }
        return null;
    }

    public Listing save(Listing entity){
        entity.setId(idCount);
        idCount++;
        entity.setListedDate("Hoje");
        listingList.add(entity);
        return entity;
    }

    public Iterable<Listing> findAll(){
        return listingRepository.findAll();
    }

    public List<Listing> findAllFromUser(User user) {
        List<Listing> userListings = new ArrayList<>();
        for (Listing l : listingList){
            if (l.getSellerId()== user.getId()){
                userListings.add(l);
            }
        }
        return userListings;
    }
}
