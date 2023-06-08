package com.catan.handler;

import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.model.PlayerResourceCard;
import com.catan.model.board.Field;
import com.catan.service.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Handler for the Thief
 * @author Agnieszka Lasek
 * @author Zuzanna Furtak
 */
@Component
public class ThiefHandler {
    private final PlayerService playerService;
    private final FieldService fieldService;
    private final GameService gameService;
    private final PlayerResourceCardService playerResourceCardService;

    /**
     * Initialize the handler.
     * @param gameService the Game service associated to this handler
     * @param playerService the Player service associated to this handler
     * @param fieldService the Field service associated to this handler
     * @param playerResourceCardService the PlayerReosurceCard service associated to this handler
     */
    public ThiefHandler(GameService gameService,
                        PlayerService playerService,
                        FieldService fieldService,
                        PlayerResourceCardService playerResourceCardService) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.fieldService = fieldService;
        this.playerResourceCardService = playerResourceCardService;
    }


    //TODO: current player can steal(random card) from player among those who have villages/cities in the chosen field
    /**
     * Activates the thief, moving it to a new field and stealing  half of the resource cards from the players if the yhave more than seven cards. Returns the game where it has been activated.
     * @param userId id of the user that activates the thief
     * @param field field where the thief is getting blocked
     * @return game where the thief is activated
     */
    public Game thief(int userId, Field field) {
        Game game = this.gameService.getGame();
        for (Player player : game.getPlayers()) {
            List<PlayerResourceCard> playerCards = this.playerResourceCardService.findAllCardsByPlayerId(player.getId());
            if (playerCards.size() > 7) {
                // for now we randomly remove half of player cards
                int numberOfCardsToRemove = playerCards.size() / 2;
                while (numberOfCardsToRemove > 0) {
                    int idToRemove = playerCards.get(0).getId();
                    this.playerResourceCardService.deleteById(idToRemove);
                    numberOfCardsToRemove--;
                }
            }
        }
        this.fieldService.unlockPrevThief();

        this.fieldService.blockNewThief(field);

        return game;
    }
}
