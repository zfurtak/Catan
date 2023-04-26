package com.catan.controller;

import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.service.PlayerService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity createPlayer(@RequestBody Player player) throws URISyntaxException {
//        Player savedPlayer = playerService.addPlayer(player);
//        return ResponseEntity.created(new URI("/player/" + savedPlayer.getId())).body(savedPlayer);
//    }

//    @PutMapping("/{id}")
//    public Player updatePlayerVictoryPoints(@PathVariable int id, @RequestBody int points){
//        return playerService.updateVictoryPoints(id, points);
//    }




}
