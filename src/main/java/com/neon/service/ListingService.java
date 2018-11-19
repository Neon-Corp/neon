package com.neon.service;

import com.neon.model.Listing;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ListingService {
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

    public List<Listing> findAll(Integer id){
        List<Listing> userListings = new ArrayList<>();
        for (Listing l : listingList){
            if (l.getSellerId()== id){
                userListings.add(l);
            }
        }
        return userListings;
    }
}
