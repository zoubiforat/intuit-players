package com.intuit.demo.service;

import com.intuit.demo.enitity.Place;
import com.intuit.demo.enitity.Player;
import com.intuit.demo.exception.PlayerNotFoundException;
import com.intuit.demo.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlaceService placeService;

    @Transactional
    public Player savePlayer(Player player) {
        if (player.getPlaceOfBirth() != null) {
            Place placeOfBirth = placeService.savePlace(player.getPlaceOfBirth());
            player.setPlaceOfBirth(placeOfBirth);
        }

        if (player.getPlaceOfDeath() != null) {
            Place placeOfDeath = placeService.savePlace(player.getPlaceOfDeath());
            player.setPlaceOfDeath(placeOfDeath);
        }

        return  this.playerRepository.save(player);
    }

    public Player getPlayerById(String playerId) {
        return this.playerRepository.findById(playerId).orElseThrow(
                () -> new PlayerNotFoundException(playerId)
        );
    }

    public List<Player> getAllPlayers() {
        return this.playerRepository.findAll();
    }

    public void resetPlayers() {
        this.playerRepository.deleteAll();
    }
}
