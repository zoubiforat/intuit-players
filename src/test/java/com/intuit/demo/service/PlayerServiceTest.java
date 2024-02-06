package com.intuit.demo.service;

import com.intuit.demo.enitity.Place;
import com.intuit.demo.enitity.Player;
import com.intuit.demo.exception.PlayerNotFoundException;
import com.intuit.demo.repository.PlaceRepository;
import com.intuit.demo.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService;

    @MockBean
    private PlayerRepository playerRepository;

    @MockBean
    private PlaceRepository placeRepository;

    @MockBean
    private PlaceService placeService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void givenInvalidPlayerId_whenGetPlayerById_thenThrowPlayerNotFoundException() {
        String invalidPlayerId = "invalidPlayerId";

        when(playerRepository.findById(invalidPlayerId)).thenReturn(Optional.empty());

        assertThrows(PlayerNotFoundException.class, () -> {
            playerService.getPlayerById(invalidPlayerId);
        });
    }

    @Test
    void givenValidPlayerId_whenSavePlayer_thenPlayerIsSavedAndCanBeRetrievedById() {
        // Arrange
        String playerId = "player123";
        Player player = Player.builder()
                .playerId(playerId)
                .firstName("John")
                .lastName("Doe")
                .build();

        // Mock behavior
        when(playerRepository.save(player)).thenReturn(player);
        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));

        // Act
        Player savedPlayer = playerService.savePlayer(player);
        Player retrievedPlayer = playerService.getPlayerById(playerId);

        // Assert
        assertNotNull(savedPlayer);
        assertNotNull(retrievedPlayer);
        assertEquals(playerId, savedPlayer.getPlayerId());
        assertEquals(playerId, retrievedPlayer.getPlayerId());
        assertEquals(player, savedPlayer);
        assertEquals(player, retrievedPlayer);
        verify(playerRepository, times(1)).save(player);
        verify(playerRepository, times(1)).findById(playerId);
    }

    @Test
    void givenValidPlayer_whenSavePlayer_thenPlayerIsSaved() {
        Player player = Player.builder()
                .playerId("player123")
                .firstName("John")
                .lastName("Doe")
                .build();

        when(playerRepository.save(player)).thenReturn(player);

        Player savedPlayer = playerService.savePlayer(player);

        assertNotNull(savedPlayer);
        assertEquals(player.getPlayerId(), savedPlayer.getPlayerId());
        verify(playerRepository, times(1)).save(player);
    }
    @Test
    void givenPlayerWithPlaces_whenSavePlayer_thenPlacesAreSavedAndPlayerIsSaved() {
        // Arrange
        Place placeOfBirth = Place.builder()
                .city("Berlin")
                .state("Berlin")
                .country("Germany")
                .build();

        Place placeOfDeath = Place.builder()
                .city("Augsburg")
                .state("Bayern")
                .country("Germany")
                .build();

        Player player = Player.builder()
                .playerId("player123")
                .firstName("John")
                .lastName("Doe")
                .placeOfBirth(placeOfBirth)
                .placeOfDeath(placeOfDeath)
                .build();

        when(placeService.savePlace(placeOfBirth)).thenReturn(placeOfBirth);
        when(placeService.savePlace(placeOfDeath)).thenReturn(placeOfDeath);
        when(playerRepository.save(player)).thenReturn(player);

        Player savedPlayer = playerService.savePlayer(player);

        assertNotNull(savedPlayer);
        assertNotNull(savedPlayer.getPlaceOfBirth());
        assertNotNull(savedPlayer.getPlaceOfDeath());
        verify(placeService, times(1)).savePlace(placeOfBirth);
        verify(placeService, times(1)).savePlace(placeOfDeath);
        verify(playerRepository, times(1)).save(player);
    }
    @Test
    void givenPlayerWithJustPlaceOfBirth_whenSavePlayer_thenPlaceOfBirthIsSavedAndPlayerIsSaved() {
        Place placeOfBirth = Place.builder()
                .city("Berlin")
                .state("Berlin")
                .country("Germany")
                .build();


        Player player = Player.builder()
                .playerId("player123")
                .firstName("John")
                .lastName("Doe")
                .placeOfBirth(placeOfBirth)
                .build();

        when(placeService.savePlace(placeOfBirth)).thenReturn(placeOfBirth);
        when(playerRepository.save(player)).thenReturn(player);

        Player savedPlayer = playerService.savePlayer(player);

        assertNotNull(savedPlayer);
        assertNotNull(savedPlayer.getPlaceOfBirth());
        assertNull(savedPlayer.getPlaceOfDeath());
        verify(placeService, times(1)).savePlace(placeOfBirth);
        verify(playerRepository, times(1)).save(player);
    }

    @Test
    void givenPlayerWithNoPlaces_whenSavePlayer_thenPlayerIsSaved() {

        Player player = Player.builder()
                .playerId("player123")
                .firstName("John")
                .lastName("Doe")
                .build();

        when(playerRepository.save(player)).thenReturn(player);

        Player savedPlayer = playerService.savePlayer(player);

        assertNotNull(savedPlayer);
        assertNull(savedPlayer.getPlaceOfBirth());
        assertNull(savedPlayer.getPlaceOfDeath());
        verify(playerRepository, times(1)).save(player);
    }


    @Test
    void givenPlayersInDatabase_whenGetAllPlayers_thenAllPlayersAreReturned() {
        Player player1 = Player.builder().playerId("player1").build();
        Player player2 = Player.builder().playerId("player2").build();

        List<Player> players = Arrays.asList(player1, player2);

        when(playerRepository.findAll()).thenReturn(players);

        List<Player> retrievedPlayers = playerService.getAllPlayers();

        assertEquals(players.size(), retrievedPlayers.size());
        assertEquals(players.get(0).getPlayerId(), retrievedPlayers.get(0).getPlayerId());
        assertEquals(players.get(1).getPlayerId(), retrievedPlayers.get(1).getPlayerId());
    }
}
