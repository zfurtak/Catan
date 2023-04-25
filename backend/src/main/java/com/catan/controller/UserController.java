package com.catan.controller;

import com.catan.model.User;
import com.catan.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public User logUser(@RequestBody String username,
                        @RequestBody String password) {
        return userService.logUserIn(username, password);
    }


}
