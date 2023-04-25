package com.catan.controller;

import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @PostMapping
    public Game createGame(@RequestBody Player initialPlayer){
        return gameService.createGame(initialPlayer);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable int id){
        gameService.deleteGameById(id);
    }

}
