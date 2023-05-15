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

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final UserService userService;

    @Autowired
    public GameService(GameRepository gameRepository,
                       PlayerService playerService,
                       UserService userService) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
        this.userService = userService;
    }

    public Game joinGame(int userId) {
        User user = userService.getUserById(userId);
        Player player = playerService.getPlayerByUserId(userId);
        player.setUser(user);
        List<Game> games = gameRepository.findAll();

        if (games.isEmpty()) {
            return createGame(player);
        } else {
            Game game = games.get(0);
            return this.joinExistingGame(game, player);
        }
    }

    public Game getGame() {
        List<Game> games = gameRepository.findAll();

        if(games.isEmpty()) {
            throw new GameNotFoundException("Game not found");
        } else {
            return games.get(0);
        }
    }

    //TODO: create fields + set good diceNumber to good field (int whichField)
    // BoardGenerator.java
    public Game createGame(Player initialPlayer) {
        List<Player> players = new ArrayList<>();
        List<Field> fields = BoardGenerator.generateFields();
        players.add(initialPlayer);
        Game newGame = new Game(players, fields);
        return gameRepository.save(newGame);
    }

    public Game joinExistingGame(Game game, Player newPlayer) {
        if (game.getPlayers().size() < 4) {
            game.getPlayers().add(newPlayer);
            return gameRepository.save(game);
        } else {
            throw new TooManyPlayersException("They are already 4 players");
        }
    }

    public void deleteGame(int id) {
        gameRepository.deleteAll();
        // not sure if it is needed (Cascade = Cascade.ALL)
        playerService.deletePlayers();
    }

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
