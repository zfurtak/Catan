package com.catan.controller;

import com.catan.model.Game;
import com.catan.model.User;
import com.catan.service.GameService;
import com.catan.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( value = "/users")
public class UserController {
    private final UserService userService;
    private final GameService gameService;

    public UserController(UserService userService,
                          GameService gameService){
        this.userService = userService;
        this.gameService = gameService;
    }


    @PostMapping(value="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registryUser(@RequestBody String username,
                                       @RequestBody String password){
        return ResponseEntity.ok(userService.registryUser(username, password));
    }


    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User logUser(@RequestBody String username,
                        @RequestBody String password) {
        return userService.logUserIn(username, password);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User getUser(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Game joinNewPlayer(@PathVariable int id) {
        return gameService.joinGame(id);
    }

}
