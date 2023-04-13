package com.catan.controller;

import com.catan.model.player.Player;
import com.catan.model.repository.PlayerRepository;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable int id) {
        return playerRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    //TODO
    // check what exception should be thrown
    public ResponseEntity createPlayer(@RequestBody Player player) throws URISyntaxException {
        Player savedPlayer = playerRepository.save(player);
        return ResponseEntity.created(new URI("/player/" + savedPlayer.getId())).body(savedPlayer);
    }

    //TODO
    // update points
    // log in
    // get player by username and password
    // send error messages when player logs in with wrong credentials + add custom exceptions

}
