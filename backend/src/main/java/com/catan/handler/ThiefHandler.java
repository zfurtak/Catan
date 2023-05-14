package com.catan.handler;

import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.model.PlayerResourceCard;
import com.catan.model.board.Field;
import com.catan.repository.GameRepository;
import com.catan.service.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ThiefHandler {
    private final PlayerService playerService;
    private final FieldService fieldService;
    private final GameService gameService;
    private final PlayerResourceCardService playerResourceCardService;

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

    public Game thief(int userId, Field field) {
        Game game = this.gameService.getGame();

        for (Player player : game.getPlayers()) {
            List<PlayerResourceCard> playerCards = this.playerResourceCardService.getPlayersCardNumber(player);
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
