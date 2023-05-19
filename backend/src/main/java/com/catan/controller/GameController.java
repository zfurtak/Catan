package com.catan.controller;

import com.catan.dto.TradeWithBankDTO;
import com.catan.handler.BuildHandler;
import com.catan.handler.ResourcesHandler;
import com.catan.handler.ThiefHandler;
import com.catan.model.Game;
import com.catan.model.board.Field;
import com.catan.model.board.Vertex;
import com.catan.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/game")
@Tag(name = "Game", description = "Game")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "405", description = "Method not allowed"),
        @ApiResponse(responseCode = "409", description = "Conflict"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")})

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class GameController {
    private final GameService gameService;
    private final ResourcesHandler resourcesHandler;
    private final ThiefHandler thiefHandler;
    private final BuildHandler buildHandler;

    public GameController(GameService gameService,
                          ResourcesHandler resourcesHandler,
                          ThiefHandler thiefHandler,
                          BuildHandler buildHandler) {
        this.gameService = gameService;
        this.thiefHandler = thiefHandler;
        this.resourcesHandler = resourcesHandler;
        this.buildHandler = buildHandler;
    }

    @PutMapping(value = "/joinGame/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "", description = "Updates the game with a new player if the user is " +
                                           "already registered and the game is not full")
    public Game joinNewPlayer(@PathVariable int userId) {
        return gameService.joinGame(userId);
    }

    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "", description = "Distributes resources among the different players of the game")
    public ResponseEntity<Void> distributeResources(@RequestBody int diceNumber) {
        resourcesHandler.distributeResources(diceNumber);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{userId}/thief", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Game thief(@PathVariable int userId, @RequestBody Field field) {
        return thiefHandler.thief(userId, field);
    }

    @GetMapping(value = "tradeWithBank/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "", description = "Returns a List of the resources that the player currently has")
    public List<Integer> getResourcesToTradeWithBank(@PathVariable int userId) {
        return gameService.getResourcesToTradeWithBank(userId);
    }


    @PutMapping(value = "tradeWithBank/{playerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "", description = "Perfoms the resource trade between the player and the bank " +
                                           "if the player has the needed resource")
    public Game tradeWithBank(@PathVariable int playerId,
                              @RequestBody TradeWithBankDTO tradeWithBankDTO){
        return gameService.tradeWithBank(playerId, tradeWithBankDTO);
    }

    @PutMapping(value = "/buildVillage/{playerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Game buildVillage(@PathVariable int playerId,
                             @RequestBody Vertex vertex) {
        return buildHandler.buildVillage(playerId, vertex);
    }

    @PutMapping(value = "/buildCity/{playerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Game buildCity(@PathVariable int playerId,
                             @RequestBody Vertex vertex) {
        return buildHandler.buildCity(playerId, vertex);
    }
}
