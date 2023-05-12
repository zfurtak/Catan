package com.catan.controller;

import com.catan.service.PlayerService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/players")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // GET - makeTradeOffer (listOfItems)
    // PUT - confirmTrade - we have current player id in Game

}
