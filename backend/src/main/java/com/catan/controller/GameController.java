package com.catan.controller;

import com.catan.dto.TradeWithBankDTO;
import com.catan.handler.ResourcesHandler;
import com.catan.handler.ThiefHandler;
import com.catan.model.Game;
import com.catan.model.board.Field;
import com.catan.service.GameService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("/game")
@Tag(name = "Game", description = "Game")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "405", description = "Method not allowed"),
        @ApiResponse(responseCode = "409", description = "Conflict"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")})

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class GameController {
    private final GameService gameService;
    private final ResourcesHandler resourcesHandler;
    private final ThiefHandler thiefHandler;

    public GameController(GameService gameService,
                          ResourcesHandler resourcesHandler,
                          ThiefHandler thiefHandler) {
        this.gameService = gameService;
        this.thiefHandler = thiefHandler;
        this.resourcesHandler = resourcesHandler;
    }

    @PutMapping(value = "/joinGame/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Game joinNewPlayer(@PathVariable int userId) {
        return gameService.joinGame(userId);
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> distributeResources(@RequestBody int diceNumber) {
        resourcesHandler.distributeResources(diceNumber);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{userId}/thief", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Game thief(@PathVariable int userId, @RequestBody Field field) {
        return thiefHandler.thief(userId, field);
    }

    @GetMapping(value = "tradeWithBank/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Integer> getResourcesToTradeWithBank(@PathVariable int userId) {
        return gameService.getResourcesToTradeWithBank(userId);
    }


    @PutMapping(value = "tradeWithBank/{playerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Game tradeWithBank(@PathVariable int playerId,
                              @RequestBody TradeWithBankDTO tradeWithBankDTO){
        return gameService.tradeWithBank(playerId, tradeWithBankDTO);
    }
}
