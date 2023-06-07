package com.catan.model.board;

import com.catan.model.Color;
import com.catan.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingTest {
    private Building building;
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player();
        player.setColor(Color.RED);

        building = new Building();
        building.setType(BuildingType.VILLAGE);
        building.setPlayer(player);
    }

    @Test
    public void testBuildingCreation() {
        assertNotNull(building);
        assertEquals(BuildingType.VILLAGE, building.getType());
        assertEquals(player, building.getPlayer());
    }

    @Test
    public void testSetBuildingType() {
        building.setType(BuildingType.CITY);
        assertEquals(BuildingType.CITY, building.getType());
    }

    @Test
    public void testSetPlayer() {
        Player newPlayer = new Player();
        newPlayer.setColor(Color.BLUE);

        building.setPlayer(newPlayer);
        assertEquals(newPlayer, building.getPlayer());
    }
}