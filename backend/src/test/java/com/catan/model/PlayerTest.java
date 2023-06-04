package com.catan.model;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class PlayerTest {

    private User user;
    private Player player;

    @BeforeEach
    void deleteBefore(){
        user = null;
        player = null;
    }

    @Test
    void createPlayerId(){
        player = new Player();
        player.setId(10);
        assertEquals(10, player.getId());
    }

    //register a new user, get said user by its name, create a new player, set the user and check its username is correct
    @Test
    void createPlayerUsername(){
        user = new User();
        player = new Player();
        player.setUser(user);
        assertEquals(user, player.getUser());
    }

    //create a new player, set its victory points to 5 and check it has 5 victory points
    @Test
    void createPlayerVictoryPoints(){
        player = new Player();
        player.setVictoryPoints(5);
        assertEquals(5, player.getVictoryPoints());
    }

    //create a new player, set its roads to 4 and check it has 4 roads
    @Test
    void createPlayerRoads(){
        player = new Player();
        player.setNumberOfRoads(4);
        assertEquals(4, player.getNumberOfRoads());
    }

    //create a new player, set its villages to 3 and check it has 3 villages
    @Test
    void createPlayerVillages(){
        player = new Player();
        player.setNumberOfVillages(3);
        assertEquals(3, player.getNumberOfVillages());
    }

    //create a new player, set its color to RED and check its color is RED
    @Test
    void createPlayerColor(){
        player = new Player();
        player.setColor(Color.RED);
        assertEquals(Color.RED, player.getColor());
    }

    //create a new player, set its cities to 2 and check it has 2 cities
    @Test
    void createPlayerCities(){
        player = new Player();
        player.setNumberOfCities(2);
        assertEquals(2, player.getNumberOfCities());
    }


}
