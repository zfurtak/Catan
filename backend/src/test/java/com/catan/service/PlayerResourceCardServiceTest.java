package com.catan.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.catan.exceptions.PlayerAlreadyExistsException;
import com.catan.exceptions.ResourceCardNotFoundException;
import com.catan.exceptions.UserNotFoundException;
import com.catan.model.Color;
import com.catan.model.Player;
import com.catan.model.PlayerResourceCard;
import com.catan.model.Resource;
import com.catan.model.User;
import com.catan.model.Game;
import com.catan.repository.GameRepository;
import com.catan.repository.PlayerRepository;
import com.catan.repository.PlayerResourceCardRepository;
import com.catan.repository.UserRepository;
import com.catan.service.GameService;
import com.catan.service.PlayerResourceCardService;
import com.catan.service.PlayerService;
import com.catan.service.UserService;

/**
 * Tests for methods from PlayerResourceCardService class.
 * @author Minerva Gomez
 */
@SpringBootTest
public class PlayerResourceCardServiceTest {
    
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
    private Player player;
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
    void addResourceCardAndFindCardsByPlayerId(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);

        servicePlayResCard.addCard(player, Resource.STONE);

        List<PlayerResourceCard> resources = servicePlayResCard.findAllCardsByPlayerId(player.getId());

        assertEquals(1, resources.size());
        assertEquals(Resource.STONE, resources.get(0).getResource());
    }

    //TO MAYBE FIX: It should not allow to save cards with a player that is not saved in the PlayerRepository
    //whether it is an IllegalArgumentException or a UserNotFoundException it should throw something
    @Test
    @Disabled
    void addResourceCardException(){
        assertThrows(IllegalArgumentException.class, () -> servicePlayResCard.addCard(player, Resource.STONE));
    }

    @Test
    void FindCardsByPlayerIdFromPlayerEmpty(){
        List<PlayerResourceCard> resources = servicePlayResCard.findAllCardsByPlayerId(0);
        assertEquals(0, resources.size());
    }

    @Test
    void deleteResourceFromPlayer(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);

        servicePlayResCard.addCard(player, Resource.STONE);
        servicePlayResCard.addCard(player, Resource.STONE);

        List<PlayerResourceCard> resources = servicePlayResCard.findAllCardsByPlayerId(player.getId());

        assertEquals(2, resources.size());

        servicePlayResCard.deleteByPlayerIdAndResource(player.getId(), Resource.STONE);

        resources = servicePlayResCard.findAllCardsByPlayerId(player.getId());

        assertEquals(1, resources.size());

    }
 
    @Test
    void deleteResourceFromPlayerWithoutCards(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);

        assertThrows(ResourceCardNotFoundException.class, () -> servicePlayResCard.deleteByPlayerIdAndResource(0, Resource.STONE));
    }

    @Test
    void deleteResourceFromNonExistingPlayer(){
        assertThrows(ResourceCardNotFoundException.class, () -> servicePlayResCard.deleteByPlayerIdAndResource(0, Resource.STONE));
    }

    @Test
    void deleteResourceFromId(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);

        servicePlayResCard.addCard(player, Resource.STONE);

        PlayerResourceCard resource = servicePlayResCard.findAllCardsByPlayerId(player.getId()).get(0);

        assertNotEquals(null, resource);

        servicePlayResCard.deleteById(resource.getId());

        List<PlayerResourceCard> resources = servicePlayResCard.findAllCardsByPlayerId(0);

        assertEquals(0, resources.size());

    }

    @Test
    void deleteResourceFromIdWithoutCards(){
        assertThrows(ResourceCardNotFoundException.class, () -> servicePlayResCard.deleteById(0));
    }

}
