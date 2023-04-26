package com.catan.controller;

import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.service.GameService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }


    @PutMapping(value = "/joinGame/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Game joinNewPlayer(@PathVariable int id) {
        return gameService.joinGame(id);
    }

    @DeleteMapping("/{id}")
    public void deleteGame(@PathVariable int id){
        gameService.deleteGameById(id);
    }



}
