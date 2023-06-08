package com.catan.model.board;

import com.catan.model.Color;
import com.catan.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class used to test the Edge class
 * @author Ivan Iroslavov
 */
class EdgeTest {
    private Player player;
    private Road road;
    private Edge edge;

    // BeforeEach is a clean way of initializating some values
    // so it will work as preconditions of each @Test, making
    // the code waaaaaay cleaner
    @BeforeEach
    public void setup() {
        // Create a sample player
        player = new Player();
        player.setId(1);
        player.setColor(Color.RED);

        // Create a sample road
        road = new Road();
        road.setId(10);
        road.setPlayer(player);

        // Create an edge
        edge = new Edge();
        edge.setId(100);
        edge.setRoad(road);
    }
    @Test
    public void testEdgeInitialization() {
        // Perform assertions
        Assertions.assertEquals(100, edge.getId());
        Assertions.assertEquals(road, edge.getRoad());
    }

    @Test
    public void testGetColorOfEdge() {
        // Perform assertion
        Assertions.assertEquals(Color.RED, edge.getColorOfEdge());
    }

}