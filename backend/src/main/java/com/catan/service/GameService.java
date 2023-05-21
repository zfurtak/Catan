package com.catan.service;

import com.catan.dto.TradeWithBankDTO;
import com.catan.exceptions.BadTradingException;
import com.catan.exceptions.GameNotFoundException;
import com.catan.exceptions.TooManyPlayersException;
import com.catan.model.*;
import com.catan.model.board.BoardGenerator;
import com.catan.model.board.Field;
import com.catan.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service for the Game class
 */
@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final UserService userService;

    /**
     * Initialize the service.
     * @param gameRepository the Game repository associated to this service
     * @param playerService the Player service associated to this service
     * @param userService the User service associated to this service
     */
    @Autowired
    public GameService(GameRepository gameRepository,
                       PlayerService playerService,
                       UserService userService) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
        this.userService = userService;
    }

    /**
     * Creates a player from the user with the specified id and let's it join a game. If there aren't any games it will call createGame.
     * @param userId id of the user that is going to join
     * @return game the player has joined to
     */
    public Game joinGame(int userId) {
        User user = userService.getUserById(userId);
        List<Game> games = gameRepository.findAll();
        Player player = playerService.createPlayer(user, playerService.getColor(games.size()));

        if (games.isEmpty()) {
            return createGame(player);
        } else {
            Game game = games.get(0);
            return this.joinExistingGame(game, player);
        }
    }

    /**
     * Creates a new game and makes the specified player join the game.
     * @param initialPlayer player who will join the new game
     * @return the created game
     */
    public Game createGame(Player initialPlayer) {
        List<Player> players = new ArrayList<>();
        List<Field> fields = BoardGenerator.generateFields();
        players.add(initialPlayer);
        Game newGame = new Game(players, fields);
        return gameRepository.save(newGame);
    }

    /**
     * Returns the game saved in the repository.
     * @exception GameNotFoundException if there are not any games saved in the repository
     * @return game
     */
    public Game getGame() {
        List<Game> games = gameRepository.findAll();

        if(games.isEmpty()) {
            throw new GameNotFoundException("Game not found");
        } else {
            return games.get(0);
        }
    }

    /**
     * Returns the game the player has just joined.
     * @exception TooManyPlayersException if there are already four players in the game
     * @param game the game the player is going to join
     * @param newPlayer the player who wants to join
     * @return the game the player has joined
     */
    public Game joinExistingGame(Game game, Player newPlayer) {
        if (game.getPlayers().size() < 4) {
            game.getPlayers().add(newPlayer);
            return gameRepository.save(game);
        } else {
            throw new TooManyPlayersException("They are already 4 players");
        }
    }

    /**
     * Deletes the game with the specified id.
     * @param id id of the game to delete
     */
    public void deleteGame(int id) {
        gameRepository.deleteAll();
        // not sure if it is needed (Cascade = Cascade.ALL)
        playerService.deletePlayers();
    }

    /**
     * Returns a list with the resources of the player with the specified id that are fit to be traded with the bank.
     * @param playerId id of the player whose resources are got
     * @return list of resource types that can be traded with the bank
     */
    public List<Integer> getResourcesToTradeWithBank(int playerId) {
        return playerService.getResourcesToTradeWithBank(playerId);
    }

    // we assume that player can exchange, because before we send list of available resources
    public Game tradeWithBank(int playerId, TradeWithBankDTO tradeWithBankDTO){
        Resource resourceFromPlayer = tradeWithBankDTO.convertPlayerResourceFromInt();
        Resource resourceFromBank = tradeWithBankDTO.convertBankResourceFromInt();

        // exception thrown if player wants to trade for the same resource
        if(resourceFromPlayer.equals(resourceFromBank)){
            throw new BadTradingException("Trying to trade the same resource");
        }

        playerService.updateCardsAfterTradingWithBank(playerId, resourceFromPlayer, resourceFromBank);
        return this.getGame();
    }

    // exception thrown if player has no resources
    // choose resources to offer
    // choose resources to get in return
    // send trade offer
    // wait for other players to accept/decline offer
    // if anyone accepted finish trade
    // if everyone declined finish trade
    // else wait (if timeout then finish trade)
    // return updated game
    public Game tradeWithPlayer(int playerId) {


        return getGame();
    }

}
