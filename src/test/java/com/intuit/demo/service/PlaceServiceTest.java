package com.intuit.demo.service;

import com.intuit.demo.enitity.Place;
import com.intuit.demo.repository.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PlaceServiceTest {
    @InjectMocks
    private PlaceService placeService;

    @MockBean
    private PlaceRepository placeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test()
    void givenNewPlace_whenSavePlace_thenSuccess() {
        Place place = Place.builder().country("Germany").city("Berlin").state("Berlin").build();
        when(placeRepository.save(place)).thenReturn(place);

        Place savedPlace = placeService.savePlace(place);

        assertEquals(place.getCountry(), savedPlace.getCountry());
        assertEquals(place.getCity(), savedPlace.getCity());
        assertEquals(place.getState(), savedPlace.getState());
        assertNotNull(savedPlace);
        verify(placeRepository, times(1)).save(place);
    }

}
