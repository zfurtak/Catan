package com.catan.controller;

import com.catan.handler.VictoryPointsHandler;
import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "Player", description = "Player")
@RequestMapping("/players")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class PlayerController {
//    private final VictoryPointsHandler victoryPointsHandler;
//
//    public PlayerController(VictoryPointsHandler victoryPointsHandler) {
//        this.victoryPointsHandler = victoryPointsHandler;
//    }

//    @Operation(summary = "", description = "Recalculates the victory points of the player based on" +
//            "the length of its largest path in the map")
//    @PutMapping(value = "updateVictoryPoints/{playerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Player updateVictoryPoints(@PathVariable int playerId) {
//        return victoryPointsHandler.updateVictoryPoints(playerId);
//    }
}
