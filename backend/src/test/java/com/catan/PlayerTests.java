package com.catan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.catan.exceptions.PasswordIncorrectException;
import com.catan.exceptions.UserAlreadyExistsException;
import com.catan.exceptions.UserNotFoundException;
import com.catan.exceptions.UsernameTooShortException;
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

    //register a new user, get said user by its name, create a new player, set the user and check its username is correct
    @Test
    void createPlayerSetUsername(){
        controlUser.registerUser("user", "pass");
        user = controlUser.getUserByName("user");
        player = new Player();
        player.setUser(user);
        assertEquals(player.getUser().getUsername(), "user");
    }

    //register a new user, get said user by its name, create a new player with that user and check it has 0 victory points
    @Test
    void createPlayerSetVictoryPoints(){
        player = new Player();
        player.setVictoryPoints(0);
        assertEquals(player.getVictoryPoints(), 0);
    }

    //register a new user, get said user by its name, create a new player with that user and check it has 0 roads
    @Test
    void createPlayerSetRoads(){
        player = new Player();
        player.setNumberOfRoads(0);
        assertEquals(player.getNumberOfRoads(), 0);
    }

    //register a new user, get said user by its name, create a new player with that user and check it has 0 villages
    @Test
    void createPlayerSetVillages(){
        player = new Player();
        player.setNumberOfVillages(0);
        assertEquals(player.getNumberOfVillages(), 0);
    }

    @Test
    void createPlayerSetColor(){
        player = new Player();
        player.setColor(Color.RED);
        assertEquals(player.getColor(), Color.RED);
    }

    //register a new user, get said user by its name, create a new player with that user and check it has 0 cities
    @Test
    void createPlayerCheckInitialCities(){
        controlUser.registerUser("user", "pass");
        user = controlUser.getUserByName("user");
        player = new Player();
        assertEquals(player.getNumberOfCities(), 0);
    }

    //register a new user, get said user by its name, create a new player with that, add the player and check it was added successfully
    @Test
    void createPlayer(){
        user = controlUser.registerUser("user", "pass");
        player = servicePlayer.createPlayer(user, Color.RED);
        assertEquals(player.getUser().getUsername(), "user");
    }
    
    //register a new user, get said user by its name, create a new player with that user, get player's id
    //get the player by its id and check it returns the player successfully
    @Test
    @Disabled
    void getPlayerFromId(){
        controlUser.registerUser("user", "pass");
        user = controlUser.getUserByName("user");
        //player = servicePlayer.createPlayer(user, Color.RED);
        //serviceGame.createGame(player); //it dies here
        serviceGame.joinGame(user.getId()); //
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
        controlUser.registerUser("user", "pass");
        user = controlUser.getUserByName("user");
        servicePlayer.createPlayer(user, Color.RED);
        serviceGame.createGame(player);
        maybe = repositoryPlayer.findByUserId(user.getId());
        player = maybe.get();
        int id = player.getId();
        servicePlayer.updateVictoryPoints(player, 3);     
        player = servicePlayer.getPlayerById(id);
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
