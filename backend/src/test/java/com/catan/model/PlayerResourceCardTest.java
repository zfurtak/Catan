package com.catan.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class PlayerResourceCardTest {
    @Mock
    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlayerResourceCardCreation() {
        // Create a resource card
        Resource resource = Resource.WOOD;

        // Create a player resource card
        PlayerResourceCard playerResourceCard = new PlayerResourceCard(mockPlayer, resource);

        assertNotNull(playerResourceCard.getId());
        assertEquals(mockPlayer, playerResourceCard.getPlayer());
        assertEquals(resource, playerResourceCard.getResource());
    }
    @Test
    void testSetPlayer() {
        // Create initial player and resource card
        Player player1 = new Player();
        Resource resource = Resource.BRICK;
        PlayerResourceCard playerResourceCard = new PlayerResourceCard(player1, resource);

        // Create a new player
        Player player2 = new Player();

        // Set the new player for the resource card
        playerResourceCard.setPlayer(player2);

        assertEquals(player2, playerResourceCard.getPlayer());
    }

    @Test
    void testSetResource() {
        // Create initial player and resource card
        Player player = new Player();
        Resource resource1 = Resource.WHEAT;
        PlayerResourceCard playerResourceCard = new PlayerResourceCard(player, resource1);

        // Change the resource type
        Resource resource2 = Resource.WOOD;
        playerResourceCard.setResource(resource2);

        assertEquals(resource2, playerResourceCard.getResource());
    }
}