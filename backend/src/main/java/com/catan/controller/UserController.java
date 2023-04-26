package com.catan.controller;

import com.catan.model.User;
import com.catan.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
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

//    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
//    public User

}
