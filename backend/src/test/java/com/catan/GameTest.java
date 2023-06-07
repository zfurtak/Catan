package com.catan;

import com.catan.exceptions.TooManyPlayersException;
import com.catan.model.Color;
import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.model.Resource;
import com.catan.model.User;
import com.catan.repository.PlayerRepository;
import com.catan.repository.PlayerResourceCardRepository;
import com.catan.repository.UserRepository;
import com.catan.service.GameService;
import com.catan.service.PlayerResourceCardService;
import com.catan.service.PlayerService;
import com.catan.service.UserService;
import com.catan.repository.GameRepository;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class GameTest {
     
    @Autowired
    private UserRepository repositoryUser;

    @Autowired
    private UserService serviceUser;

    @Autowired
    private PlayerResourceCardRepository repositoryPlayResCard;
    
    @Autowired
    private PlayerResourceCardService servicePlayResCard;

    @Autowired
    private PlayerRepository repositoryPlayer;

    @Autowired
    private PlayerService servicePlayer;

    @Autowired
    private GameRepository repositoryGame;

    @Autowired
    private GameService serviceGame;

    private User user;
    private User user2;
    private User user3;
    private User user4;
    private User user5;
    private Player player;
    private Player player2;
    private Player player3; 
    private Player player4;  
    private Player player5;      
    private Game game;

    @BeforeEach
    void deleteBefore(){
        repositoryGame.deleteAll();
        repositoryPlayResCard.deleteAll();
        repositoryPlayer.deleteAll();
        repositoryUser.deleteAll();
    }

    @AfterEach
    void deleteAfter(){
        repositoryGame.deleteAll();
        repositoryPlayResCard.deleteAll();
        repositoryPlayer.deleteAll();
        repositoryUser.deleteAll();
    }


    @Test
    void createGameFromJoinGame(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        assertNotNull(game);
        player = game.getPlayers().get(0);  
        assertNotNull(player);

    }

    @Test
    void fourPlayersJoinGameColors(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);

        user2 = serviceUser.registerUser("user2", "pass2");
        game = serviceGame.joinGame(user2.getId()); 
        player2 = game.getPlayers().get(1);

        user3 = serviceUser.registerUser("user3", "pass3");
        game = serviceGame.joinGame(user3.getId()); 
        player3 = game.getPlayers().get(2);

        user4 = serviceUser.registerUser("user4", "pass4");
        game = serviceGame.joinGame(user4.getId()); 
        player4 = game.getPlayers().get(3);

        assertEquals(player.getColor(), Color.RED);
        assertEquals(player2.getColor(), Color.BLUE);
        assertEquals(player3.getColor(), Color.YELLOW);
        assertEquals(player4.getColor(), Color.WHITE);
    }

    @Test
    void fourPlayersJoinGame(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);

        user2 = serviceUser.registerUser("user2", "pass2");
        game = serviceGame.joinGame(user2.getId()); 
        player2 = game.getPlayers().get(1);

        user3 = serviceUser.registerUser("user3", "pass3");
        game = serviceGame.joinGame(user3.getId()); 
        player3 = game.getPlayers().get(2);

        user4 = serviceUser.registerUser("user4", "pass4");
        game = serviceGame.joinGame(user4.getId()); 
        player4 = game.getPlayers().get(3);

        assertEquals(game.getPlayers().size(), 4);
    }

    @Test
    void fithPlayerTriesToJoinGameException(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 

        user2 = serviceUser.registerUser("user2", "pass2");
        game = serviceGame.joinGame(user2.getId()); 

        user3 = serviceUser.registerUser("user3", "pass3");
        game = serviceGame.joinGame(user3.getId()); 

        user4 = serviceUser.registerUser("user4", "pass4");
        game = serviceGame.joinGame(user4.getId()); 

        user5 = serviceUser.registerUser("user5", "pass5");

        assertThrows(TooManyPlayersException.class, () -> serviceGame.joinGame(user5.getId()));
    }

    @Test
    @Disabled
    void playersJoinExistingGame(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 

        user2 = serviceUser.registerUser("user2", "pass2");
        game = serviceGame.joinGame(user2.getId()); 

        user3 = serviceUser.registerUser("user3", "pass3");
        game = serviceGame.joinGame(user3.getId()); 

        user4 = serviceUser.registerUser("user4", "pass4");
        game = serviceGame.joinGame(user4.getId()); 

        servicePlayResCard.addCard(player, Resource.STONE);

        serviceGame.deleteGame(game.getId());

        assertTrue(repositoryGame.findAll().isEmpty());
        assertTrue(repositoryPlayer.findAll().isEmpty());
        assertTrue(repositoryPlayResCard.findAll().isEmpty());
        assertTrue(repositoryPlayer.findAll().isEmpty());
        assertTrue(repositoryPlayer.findAll().isEmpty());
        assertTrue(repositoryPlayer.findAll().isEmpty());

    }




}