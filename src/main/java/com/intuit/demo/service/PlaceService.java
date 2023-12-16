package com.intuit.demo.service;

import com.intuit.demo.enitity.Place;
import com.intuit.demo.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public Place savePlace(Place place) {
        return this.placeRepository.save(place);
    }
}
