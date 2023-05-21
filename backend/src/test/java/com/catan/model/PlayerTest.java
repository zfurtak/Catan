package com.catan.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class PlayerTest {
    @Test
    public void testPlayerInitialization() {
        // Create a sample user
        User user = new User();
        user.setId(1);

        // Create a player
        Player player = new Player();
        player.setId(10);
        player.setUser(user);
        player.setColor(Color.RED);
        player.setVictoryPoints(5);
        player.setNumberOfRoads(4);
        player.setNumberOfVillages(3);
        player.setNumberOfCities(2);

        // Perform assertions
        Assertions.assertEquals(10, player.getId());
        Assertions.assertEquals(user, player.getUser());
        Assertions.assertEquals(Color.RED, player.getColor());
        Assertions.assertEquals(5, player.getVictoryPoints());
        Assertions.assertEquals(4, player.getNumberOfRoads());
        Assertions.assertEquals(3, player.getNumberOfVillages());
        Assertions.assertEquals(2, player.getNumberOfCities());
    }

}