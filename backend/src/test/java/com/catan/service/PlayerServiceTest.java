package com.catan.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.catan.exceptions.IncorrectGamesNumberException;
import com.catan.exceptions.PlayerAlreadyExistsException;
import com.catan.exceptions.ResourceCardNotFoundException;
import com.catan.exceptions.TooManyPlayersException;
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
 * Tests for methods from PlayerService class.
 * @author Minerva Gomez
 */
@SpringBootTest
public class PlayerServiceTest {
    
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
    private Player player;
    private Player player2;
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

    //register a new user, get said user by its name, create a new player with that, add the player and check it was added successfully
    @Test
    void createPlayerUser(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);
        assertEquals(user.getId(), player.getUser().getId());
    }

    @Test
    void createPlayerVictoryPoints(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);
        assertEquals(0, player.getVictoryPoints());
    }

    @Test
    void createPlayerRoads(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);
        assertEquals(0, player.getNumberOfRoads());
    }

    @Test
    void createPlayerVillages(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);
        assertEquals(0, player.getNumberOfVillages());
    }

    @Test
    void createPlayerCities(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);
        assertEquals(0, player.getNumberOfCities());
    }

    @Test
    void createPlayerException(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        assertThrows(PlayerAlreadyExistsException.class, () -> serviceGame.joinGame(user.getId()));
    }

    @Test
    void getPlayerFromId(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId());
        player = game.getPlayers().get(0); 
        int id = player.getId();
        player = servicePlayer.getPlayerById(id);
        assertEquals(user.getId(), player.getUser().getId());
    }

    @Test
    void createPlayerId(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);
        assertEquals(user.getId(), player.getUser().getId());
    }

    //using getPlayerById when player does not exist throws UserNotFoundException
    //(although it might be better to have a PlayerNotFoundException)
    @Test
    void getPlayerFromIdNotFoundExceptionWhenEmpty(){
        assertThrows(UserNotFoundException.class, () -> servicePlayer.getPlayerById(0));
    }

    @Test
    void getPlayerFromIdNotFoundExceptionWithPlayers(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);
        assertThrows(UserNotFoundException.class, () -> servicePlayer.getPlayerById(player.getId()+1));
    }

    //IMPORTANT: it updates the player's points, but the players of the game don't get updated
    @Test
    void updateVictoryPointsFromPlayer(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);
        player = servicePlayer.updateVictoryPoints(player, 3);
        assertEquals(3, player.getVictoryPoints() );
    }

    @Test
    void updateVictoryPointsFromId(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);
        player = servicePlayer.updateVictoryPoints(player.getId(), 3);
        assertEquals(3, player.getVictoryPoints());
    }
   
    @Test
    void deleteAllPlayers(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        
        user2 = serviceUser.registerUser("name", "word");
        game = serviceGame.joinGame(user2.getId());

        player = game.getPlayers().get(0);
        player2 = game.getPlayers().get(1);

        servicePlayer.deletePlayers();

        assertThrows(UserNotFoundException.class, () -> servicePlayer.getPlayerById(user.getId()));
        assertThrows(UserNotFoundException.class, () -> servicePlayer.getPlayerById(user2.getId()));
    }

    @Test
    void getPlayersResources(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);

        servicePlayResCard.addCard(player, Resource.STONE);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.BRICK);

        Map<Resource, Integer> resources = servicePlayer.getPlayerResources(player.getId()); 
        
        Map<Resource, Integer> result = new HashMap<>();
        result.put(Resource.STONE, 1);
        result.put(Resource.WOOD, 3);
        result.put(Resource.BRICK, 1);

        assertEquals(result, resources);
    }

    //register a new user, get said user by its name, create a new player with that user
    //get the map containing the total resources of the player without adding anything
    //check the returned map is empty  
    @Test
    void getPlayersResourcesEmpty(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);

        Map<Resource, Integer> resources = servicePlayer.getPlayerResources(player.getId()); 
        
        Map<Resource, Integer> result = new HashMap<>();

        assertEquals(result, resources);
    }

    @Test
    void getPlayersResourcesTradeBank(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);

        servicePlayResCard.addCard(player, Resource.STONE);     //1 unit of STONE
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);      //5 units of WOOD
        servicePlayResCard.addCard(player, Resource.BRICK);
        servicePlayResCard.addCard(player, Resource.BRICK);
        servicePlayResCard.addCard(player, Resource.BRICK);
        servicePlayResCard.addCard(player, Resource.BRICK);     //4 units of BRICK

        List<Integer> resources = servicePlayer.getResourcesToTradeWithBank(player.getId());
        
        assertEquals(2, resources.size());
        assertTrue(resources.contains(0));
        assertTrue(resources.contains(1));
    }

    @Test
    void updateAfterTradingWithBank(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);

        servicePlayResCard.addCard(player, Resource.STONE);     //1 unit of STONE
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);      //5 units of WOOD
        servicePlayResCard.addCard(player, Resource.BRICK);
        servicePlayResCard.addCard(player, Resource.BRICK);     //2 units of BRICK

        //give 4 of WOOD for 1 WHEAT
        servicePlayer.updateCardsAfterTradingWithBank(player.getId(), Resource.WOOD, Resource.WHEAT);   

        Map<Resource, Integer> resources = servicePlayer.getPlayerResources(player.getId());
        
        Map<Resource, Integer> result = new HashMap<>();
        result.put(Resource.STONE, 1);
        result.put(Resource.WOOD, 1);
        result.put(Resource.BRICK, 2);
        result.put(Resource.WHEAT, 1);

        assertEquals(result, resources);
    }

    @Test
    void updateAfterTradingWithBankNotEnough(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);

        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);

        assertThrows(ResourceCardNotFoundException.class, () -> servicePlayer.updateCardsAfterTradingWithBank(player.getId(), Resource.WOOD, Resource.WHEAT));
    }

    @Test
    void updateAfterTradingWithPlayer(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);

        user2 = serviceUser.registerUser("name", "word");
        game = serviceGame.joinGame(user2.getId()); 
        player2 = game.getPlayers().get(1);

        servicePlayResCard.addCard(player, Resource.STONE);     //1 unit of STONE
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);      //5 units of WOOD
        servicePlayResCard.addCard(player, Resource.BRICK);
        servicePlayResCard.addCard(player, Resource.BRICK);     //2 units of BRICK

        servicePlayResCard.addCard(player2, Resource.WHEAT);
        servicePlayResCard.addCard(player2, Resource.WHEAT);
        servicePlayResCard.addCard(player2, Resource.WHEAT);    //3 units of WHEAT

        //give 4 of WOOD for 2 WHEAT
        servicePlayer.updateCardsAfterTradingWithPlayer(player.getId(), player2.getId(), Resource.WOOD, Resource.WHEAT, 4, 2);  

        Map<Resource, Integer> resources1 = servicePlayer.getPlayerResources(player.getId());
        
        Map<Resource, Integer> result1 = new HashMap<>();
        result1.put(Resource.STONE, 1);
        result1.put(Resource.WOOD, 1);  // -4 of WOOD
        result1.put(Resource.BRICK, 2);
        result1.put(Resource.WHEAT, 2); // +2 of WHEAT

        Map<Resource, Integer> resources2 = servicePlayer.getPlayerResources(player2.getId());

        Map<Resource, Integer> result2 = new HashMap<>();
        result2.put(Resource.WOOD, 4);  // +4 of WOOD
        result2.put(Resource.WHEAT, 1); // -2 of WHEAT

        assertEquals(result1, resources1);
        assertEquals(result2, resources2);
    }

    @Test
    void updateAfterTradingWithPlayerWhenNotEnoughResources(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);

        user2 = serviceUser.registerUser("name", "word");
        game = serviceGame.joinGame(user2.getId()); 
        player2 = game.getPlayers().get(1);

        servicePlayResCard.addCard(player, Resource.STONE);     //1 unit of STONE

        servicePlayResCard.addCard(player2, Resource.WHEAT);
        servicePlayResCard.addCard(player2, Resource.WHEAT);
        servicePlayResCard.addCard(player2, Resource.WHEAT);    //3 units of WHEAT

        //give 4 of WOOD for 2 WHEAT, but player does not have enough
        assertThrows(ResourceCardNotFoundException.class, () -> servicePlayer.updateCardsAfterTradingWithPlayer(player.getId(), player2.getId(), Resource.WOOD, Resource.WHEAT, 4, 2));  

    }

    @Test
    void updateAfterTradingWithNonExistingPlayer(){
        user = serviceUser.registerUser("user", "pass");
        game = serviceGame.joinGame(user.getId()); 
        player = game.getPlayers().get(0);

        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);

        //give 4 of WOOD for 2 WHEAT, but player2 is not in the game
        assertThrows(UserNotFoundException.class, () -> servicePlayer.updateCardsAfterTradingWithPlayer(player.getId(), 0, Resource.WOOD, Resource.WHEAT, 4, 2));  

    }

    @Test
    void getColors(){
        assertEquals(servicePlayer.getColor(0), Color.RED);
        assertEquals(servicePlayer.getColor(1), Color.BLUE);
        assertEquals(servicePlayer.getColor(2), Color.YELLOW);
        assertEquals(servicePlayer.getColor(3), Color.WHITE);
    }

    @Test
    void getColorsException(){
        assertThrows(TooManyPlayersException.class, () -> servicePlayer.getColor(4));
    }


}
