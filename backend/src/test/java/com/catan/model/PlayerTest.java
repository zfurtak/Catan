package com.catan.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player();
    }

    @Test
    public void testPlayerCreation() {
        assertNotNull(player);
        assertEquals(0, player.getId());
        assertNull(player.getUser());
        assertNull(player.getColor());
        assertEquals(0, player.getVictoryPoints());
        assertEquals(0, player.getNumberOfRoads());
        assertEquals(0, player.getNumberOfVillages());
        assertEquals(0, player.getNumberOfCities());
    }

    @Test
    public void testSettersAndGetters() {
        User user = new User();
        player.setId(1);
        player.setUser(user);
        player.setColor(Color.RED);
        player.setVictoryPoints(5);
        player.setNumberOfRoads(3);
        player.setNumberOfVillages(2);
        player.setNumberOfCities(1);

        assertEquals(1, player.getId());
        assertEquals(user, player.getUser());
        assertEquals(Color.RED, player.getColor());
        assertEquals(5, player.getVictoryPoints());
        assertEquals(3, player.getNumberOfRoads());
        assertEquals(2, player.getNumberOfVillages());
        assertEquals(1, player.getNumberOfCities());
    }


}