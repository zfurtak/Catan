package com.catan.model;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.catan.exceptions.UserNotFoundException;
import com.catan.model.Color;
import com.catan.model.Player;
import com.catan.model.PlayerResourceCard;
import com.catan.model.Resource;
import com.catan.model.User;
import com.catan.repository.GameRepository;
import com.catan.repository.PlayerRepository;
import com.catan.repository.PlayerResourceCardRepository;
import com.catan.repository.UserRepository;
import com.catan.service.GameService;
import com.catan.service.PlayerResourceCardService;
import com.catan.service.PlayerService;
import com.catan.service.UserService;

@SpringBootTest
public class PlayerTests {

    @Autowired
    private UserRepository repositoryUser;

    @Autowired
    private UserService controlUser;

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
    private Optional<Player> maybe;

    @BeforeEach
    void deleteBefore(){
        repositoryUser.deleteAll();
        repositoryPlayer.deleteAll();
        repositoryPlayResCard.deleteAll();
        repositoryGame.deleteAll();
    }

    @AfterEach
    void deleteAfter(){
        repositoryUser.deleteAll();
        repositoryPlayer.deleteAll();
        repositoryPlayResCard.deleteAll();
        repositoryGame.deleteAll();
    }

    @Test
    void createPlayerSetId(){
        player = new Player();
        player.setId(10);
        assertEquals(10, player.getId());
    }

    //register a new user, get said user by its name, create a new player, set the user and check its username is correct
    @Test
    void createPlayerSetUsername(){
        user = controlUser.registerUser("user", "pass");
        player = new Player();
        player.setUser(user);
        assertEquals(user, player.getUser());
    }

    //create a new player, set its victory points to 5 and check it has 5 victory points
    @Test
    void createPlayerSetVictoryPoints(){
        player = new Player();
        player.setVictoryPoints(5);
        assertEquals(5, player.getVictoryPoints());
    }

    //create a new player, set its roads to 4 and check it has 4 roads
    @Test
    void createPlayerSetRoads(){
        player = new Player();
        player.setNumberOfRoads(4);
        assertEquals(4, player.getNumberOfRoads());
    }

    //create a new player, set its villages to 3 and check it has 3 villages
    @Test
    void createPlayerSetVillages(){
        player = new Player();
        player.setNumberOfVillages(3);
        assertEquals(3, player.getNumberOfVillages());
    }

    //create a new player, set its color to RED and check its color is RED
    @Test
    void createPlayerSetColor(){
        player = new Player();
        player.setColor(Color.RED);
        assertEquals(Color.RED, player.getColor());
    }

    //create a new player, set its cities to 2 and check it has 2 cities
    @Test
    void createPlayerSetCities(){
        player = new Player();
        player.setNumberOfCities(2);
        assertEquals(2, player.getNumberOfCities());
    }

    //register a new user, get said user by its name, create a new player with that, add the player and check it was added successfully
    @Test
    @Disabled
    void createPlayer(){
        user = controlUser.registerUser("user", "pass");
        serviceGame.joinGame(user.getId()); 
        maybe = repositoryPlayer.findByUserId(user.getId());
        player = maybe.get();
        assertEquals(player.getUser().getUsername(), "user");
    }
    
    //register a new user, get said user by its name, create a new player with that user, get player's id
    //get the player by its id and check it returns the player successfully
    @Test
    @Disabled
    void getPlayerFromId(){
        user = controlUser.registerUser("user", "pass");
        serviceGame.joinGame(user.getId()); 
        maybe = repositoryPlayer.findByUserId(user.getId());
        player = maybe.get();
        int id = player.getId();
        player = servicePlayer.getPlayerById(id);
        assertEquals(player.getUser().getUsername(), "user");
    }

    //using getPlayerById when player does not exist throws UserNotFoundException
    //(although it might be better to have a PlayerNotFoundException)
    @Test
    void getPlayerFromIdNotFoundException(){
        assertThrows(UserNotFoundException.class, () -> servicePlayer.getPlayerById(0));
    }

    //register a new user, get said user by its name, create a new player with that user
    //update its victory points using the player and check the victory points were updated successfully
    @Test
    @Disabled
    void updateVictoryPointsFromPlayer(){
        user = controlUser.registerUser("user", "pass");
        servicePlayer.createPlayer(user, Color.RED);
        serviceGame.createGame(player);
        servicePlayer.updateVictoryPoints(player, 3);     
        player = servicePlayer.getPlayerById(player.getId());
        assertEquals(player.getVictoryPoints(), 3);
    }

    //register a new user, get said user by its name, create a new player with that user, get the player's id
    //update its victory points using the id and check the victory points were updated successfully
    @Test
    @Disabled
    void updateVictoryPointsFromId(){
        controlUser.registerUser("user", "pass");
        user = controlUser.getUserByName("user");
        player = new Player();
        servicePlayer.createPlayer(user, Color.RED);
        player = servicePlayer.getPlayerById(user.getId());
        servicePlayer.updateVictoryPoints(player.getId(), 3);     
        player = servicePlayer.getPlayerById(user.getId());
        assertEquals(player.getVictoryPoints(), 3);
    }
   
    //register two new users, get said users by their names, create two new players with those users
    //use deletePlayers and check that trying to retrieve those players throws UserNotFoundException 
    @Test
    @Disabled
    void deleteAllPlayers(){
        controlUser.registerUser("user", "pass");
        user = controlUser.getUserByName("user");
        player = new Player();
        player = servicePlayer.createPlayer(user, Color.RED);

        controlUser.registerUser("name", "word");
        user2 = controlUser.getUserByName("user");
        player2 = new Player();
        player2 = servicePlayer.createPlayer(user, Color.RED);

        servicePlayer.deletePlayers();

        assertThrows(UserNotFoundException.class, () -> servicePlayer.getPlayerById(user.getId()));
        assertThrows(UserNotFoundException.class, () -> servicePlayer.getPlayerById(user2.getId()));
    }

    //register a new user, get said user by its name, create a new player with that user
    //add 3 wood, 1 wool and 1 brick to the player's resources, get the map containing the total resources of the player
    //use a map with the correct output to check it returns a map with the correct values
    @Test
    @Disabled
    void getPlayersResources(){
        controlUser.registerUser("user", "pass");
        user = controlUser.getUserByName("user");
        player = new Player();
        servicePlayer.createPlayer(user, Color.RED);
        player = servicePlayer.getPlayerById(user.getId());

        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.WOOL);
        servicePlayResCard.addCard(player, Resource.WOOD);
        servicePlayResCard.addCard(player, Resource.BRICK);

        Map<Resource, Integer> resources = servicePlayer.getPlayerResources(player.getId()); 
        
        Map<Resource, Integer> result = new HashMap<>();
        result.put(Resource.WOOD, 3);
        result.put(Resource.WOOL, 1);
        result.put(Resource.BRICK, 1);

        assertEquals(resources, result);
    }

    //register a new user, get said user by its name, create a new player with that user
    //get the map containing the total resources of the player without adding anything
    //check the returned map is empty  
    @Test
    @Disabled
    void getPlayersResourcesEmpty(){
        controlUser.registerUser("user", "pass");
        user = controlUser.getUserByName("user");
        player = new Player();
        servicePlayer.createPlayer(user, Color.RED);
        player = servicePlayer.getPlayerById(user.getId());

        Map<Resource, Integer> resources = servicePlayer.getPlayerResources(player.getId()); 
        
        Map<Resource, Integer> result = new HashMap<>();

        assertEquals(resources, result);
    }
}
