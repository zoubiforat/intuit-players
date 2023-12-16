package com.intuit.demo.repository;

import com.intuit.demo.enitity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
