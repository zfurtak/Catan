package com.catan.controller;

import com.catan.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Tag(name = "Player", description = "Player")
@RequestMapping("/players")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // GET - makeTradeOffer (listOfItems)
    // PUT - confirmTrade - we have current player id in Game

    @GetMapping(value = "/tradeWithBank/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "", description = "Returns a List of the resources that the player currently has")
    public List<Integer> getResourcesToTradeWithBank(@PathVariable int playerId) {
        return playerService.getResourcesToTradeWithBank(playerId);
    }

//    @GetMapping(value = "/tradeWithPlayer/{playerOfferingId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public TradingDTO getResourcesToTradeWithPlayer(@PathVariable int playerId) {

//    }
}
