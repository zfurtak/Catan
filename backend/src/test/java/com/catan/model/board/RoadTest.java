package com.catan.model.board;

import com.catan.model.Color;
import com.catan.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RoadTest {
    private Player player;
    private Road road;
    @BeforeEach
    public void setup() {
        // Create a sample player
        player = new Player();
        player.setId(1);
        player.setColor(Color.RED);

        // Create a road
        road = new Road();
        road.setId(10);
        road.setPlayer(player);
        road.setRoadLength(3);
    }

    @Test
    public void testRoadInitialization() {
        // Perform assertions
        Assertions.assertEquals(10, road.getId());
        Assertions.assertEquals(player, road.getPlayer());
        Assertions.assertEquals(3, road.getRoadLength());
    }

}