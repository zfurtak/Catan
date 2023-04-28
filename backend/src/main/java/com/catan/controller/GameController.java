package com.catan.controller;

import com.catan.handler.ResourcesHandler;
import com.catan.model.Game;
import com.catan.service.GameService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController("/game")
public class GameController {
    private final GameService gameService;
    private final ResourcesHandler resourcesHandler;

    public GameController(GameService gameService,
                          ResourcesHandler resourcesHandler){
        this.gameService = gameService;
        this.resourcesHandler = resourcesHandler;
    }


    @PutMapping(value = "/joinGame/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Game joinNewPlayer(@PathVariable int userId) {
        return gameService.joinGame(userId);
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Game distributeResources(@RequestBody int diceNumber){
        return resourcesHandler.distributeResources(diceNumber);
    }

    @PutMapping(value = "/{userId}/thief", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Game thief(@PathVariable int userId){
        return gameService.thief(userId);
    }


}
