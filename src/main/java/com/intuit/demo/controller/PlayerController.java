package com.intuit.demo.controller;

import com.intuit.demo.enitity.Player;
import com.intuit.demo.service.PlayerService;
import com.intuit.demo.service.CSVService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/api/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @Autowired
    private CSVService csvService;

    @GetMapping()
    public List<Player> getAllProducts() {
        return this.playerService.getAllPlayers();
    }


    @GetMapping("/{playerID}")
    public Player getPlayerById(@PathVariable String playerID) {
        return this.playerService.getPlayerById(playerID);
    }

    @PostMapping("/import")
    public void importPlayers(@RequestParam("file") MultipartFile file) {
        this.csvService.importPlayers(file);
    }

    @GetMapping("/reset")
    public void resetPlayers() {
        this.playerService.resetPlayers();
    }
}
