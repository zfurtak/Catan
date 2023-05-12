package com.catan.controller;

import com.catan.model.Game;
import com.catan.model.User;
import com.catan.service.GameService;
import com.catan.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( value = "/users")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class UserController {
    private final UserService userService;
    private final GameService gameService;

    public UserController(UserService userService,
                          GameService gameService){
        this.userService = userService;
        this.gameService = gameService;
    }


    @PostMapping(value="/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerUser(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        return ResponseEntity.ok(userService.registerUser(username, password));
    }

    @PostMapping(value = "/logIn", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User logUser(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        return userService.logUserIn(username, password);
    }

    @GetMapping(value = "/getAllUsers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getALlUsers());
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User getUser(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping(value = "/joinGame/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Game joinNewPlayer(@PathVariable int id) {
        return gameService.joinGame(id);
    }
    @DeleteMapping(value = "/deleteUserById/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User deleteUserById(@PathVariable int id){
        return userService.deleteUserById(id);
    }

    @PutMapping(value = "/updateUserById/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public User updateUserById(@PathVariable int id, @RequestBody User user){
        return userService.updateUserById(id, user);
    }

}
