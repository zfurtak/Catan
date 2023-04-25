package com.catan.controller;

import com.catan.model.Player;
import com.catan.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    //TODO: not yet implemented
    @GetMapping("/{id}")
    public Player joinGame(@PathVariable int id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping
    public ResponseEntity createPlayer(@RequestBody Player player) throws URISyntaxException {
        Player savedPlayer = playerService.addPlayer(player);
        return ResponseEntity.created(new URI("/player/" + savedPlayer.getId())).body(savedPlayer);
    }

    @PutMapping("/{id}")
    public Player updatePlayerVictoryPoints(@PathVariable int id, @RequestBody int points){
        return playerService.updatePoints(id, points);
    }


}
