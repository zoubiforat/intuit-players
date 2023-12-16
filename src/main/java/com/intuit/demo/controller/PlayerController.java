package com.intuit.demo.controller;

import com.intuit.demo.enitity.Player;
import com.intuit.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping()
    public List<Player> getAllProducts() {
        return this.playerService.getAllPlayers();
    }


    @GetMapping("/{playerID}")
    public Player getPlayerById(@PathVariable String playerID) {
        return this.playerService.getPlayerById(playerID);
    }
}
