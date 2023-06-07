package com.catan.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDevCardsTest {
    private PlayerDevCards playerDevCards;
    private Player player;
    private DevelopmentCard developmentCard;

    @BeforeEach
    public void setUp() {
        player = new Player();
        developmentCard = new DevelopmentCard();
        playerDevCards = new PlayerDevCards();
        playerDevCards.setPlayer(player);
        playerDevCards.setDevelopmentCard(developmentCard);
    }

    @Test
    public void testPlayerDevCardsCreation() {
        assertNotNull(playerDevCards);
        assertEquals(0, playerDevCards.getId());
        assertEquals(player, playerDevCards.getPlayer());
        assertEquals(developmentCard, playerDevCards.getDevelopmentCard());
    }

    @Test
    public void testSettersAndGetters() {
        Player newPlayer = new Player();
        DevelopmentCard newDevelopmentCard = new DevelopmentCard();
        playerDevCards.setId(1);
        playerDevCards.setPlayer(newPlayer);
        playerDevCards.setDevelopmentCard(newDevelopmentCard);

        assertEquals(1, playerDevCards.getId());
        assertEquals(newPlayer, playerDevCards.getPlayer());
        assertEquals(newDevelopmentCard, playerDevCards.getDevelopmentCard());
    }
    

}