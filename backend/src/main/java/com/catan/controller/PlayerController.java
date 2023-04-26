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

    // GET - makeTradeOffer (listOfItems)
    // PUT - confirmTrade - we have current player id in Game

}
