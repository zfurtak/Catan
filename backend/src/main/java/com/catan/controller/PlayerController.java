package com.catan.controller;

import com.catan.model.Player;
import com.catan.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

// ---------------------------------------------------------------
//hej Aga
// pamiętaj, że controller nie gada z repository tylko z servicem
// have a nice day bby
//----------------------------------------------------------------

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    public Player getPlayerByName(@RequestBody String username) {
        return playerService.getPlayerByName(username);
    }

    @GetMapping("/{id}")
    public Player joinGame(@PathVariable int id) {
        return playerService.getPlayerById(id);
    }

//    @GetMapping()
//    public Player logPlayer(@RequestBody String username,
//                            @RequestBody String password) {
//        return playerService.logPlayerIn(username, password);
//    }

    @PostMapping
    //TODO
    // check what exception should be thrown
    // jeśli tutaj jest player już gotowy to gdzie się generuje ID? w bazie czy na froncie czy spring to robi
    public ResponseEntity createPlayer(@RequestBody Player player) throws URISyntaxException {
        Player savedPlayer = playerService.addPlayer(player);
        return ResponseEntity.created(new URI("/player/" + savedPlayer.getId())).body(savedPlayer);
    }

    @PutMapping("/{id}")
    public Player updatePlayerPoints(@PathVariable int id, @RequestBody int points){
        return playerService.updatePoints(id, points);
    }


}
