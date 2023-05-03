package com.catan.controller;

import com.catan.handler.ResourcesHandler;
import com.catan.handler.ThiefHandler;
import com.catan.model.Game;
import com.catan.model.board.Field;
import com.catan.service.GameService;
import org.hibernate.mapping.Any;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController("/game")
public class GameController {
    private final GameService gameService;
    private final ResourcesHandler resourcesHandler;
    private final ThiefHandler thiefHandler;

    public GameController(GameService gameService,
                          ResourcesHandler resourcesHandler,
                          ThiefHandler thiefHandler){
        this.gameService = gameService;
        this.thiefHandler = thiefHandler;
        this.resourcesHandler = resourcesHandler;
    }


    @PutMapping(value = "/joinGame/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Game joinNewPlayer(@PathVariable int userId) {
        return gameService.joinGame(userId);
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> distributeResources(@RequestBody int diceNumber){
        resourcesHandler.distributeResources(diceNumber);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{userId}/thief", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Game thief(@PathVariable int userId, @RequestBody Field field){
        return thiefHandler.thief(userId, field);
    }


}
