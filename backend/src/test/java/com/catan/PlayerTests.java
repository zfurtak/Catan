package com.catan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.catan.exceptions.PasswordIncorrectException;
import com.catan.exceptions.UserAlreadyExistsException;
import com.catan.exceptions.UserNotFoundException;
import com.catan.exceptions.UsernameTooShortException;
import com.catan.model.Player;
import com.catan.model.PlayerResourceCard;
import com.catan.model.User;
import com.catan.repository.PlayerRepository;
import com.catan.repository.PlayerResourceCardRepository;
import com.catan.repository.UserRepository;
import com.catan.service.PlayerResourceCardService;
import com.catan.service.PlayerService;
import com.catan.service.UserService;

public class PlayerTests {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService control;

    @Autowired
    private PlayerResourceCardRepository repositoryPlayResCard;
    
    @Autowired
    private PlayerResourceCardService controlPlayResCard;

    @Autowired
    private PlayerRepository repositoryPlayer;

    @Autowired
    private PlayerService controlPlayer;

    private User user;
    private Player player;


    @Test
    @Disabled
    void createPlayer(){
        control.registerUser("user", "pass");
        user = control.getUserByName("user");
        //player = new Player(user);
        player = controlPlayer.addPlayer(player);
    }
    
}
