package com.catan.service;

import com.catan.dto.TradingDTO;
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
        List<Game> games = gameRepository.findAll();
        int color = 0;
        if(!games.isEmpty()){
            color = games.get(0).getPlayers().size();
        }
        Player player = playerService.createPlayer(user, playerService.getColor(color));

        if (games.isEmpty()) {
            return createGame(player);
        } else {
            Game game = games.get(0);
            return this.joinExistingGame(game, player);
        }
    }
    public Game createGame(Player initialPlayer) {
        List<Player> players = new ArrayList<>();
        List<Field> fields = BoardGenerator.generateFields();
        players.add(initialPlayer);
        Game newGame = new Game(players, fields);
        return gameRepository.save(newGame);
    }

    public Game getGame() {
        List<Game> games = gameRepository.findAll();

        if(games.isEmpty()) {
            throw new GameNotFoundException("Game not found");
        } else {
            return games.get(0);
        }
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
    public Game tradeWithBank(int playerId, TradingDTO tradingDTO){
        Resource resourceFromPlayer = tradingDTO.convertPlayerResourceFromInt();
        Resource resourceFromBank = tradingDTO.convertBankResourceFromInt();

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
    // we assume that one of the players offered a trade deal and one of the other players accepted it
    public Game tradeWithPlayer(int offeringPlayerId, int acceptingPlayerId, TradingDTO tradingDTO) {
        Resource resourceToOffer = tradingDTO.convertResourceToOffer();
        Resource resourceToGet = tradingDTO.convertResourceToGet();
        int numOfResourcesToOffer = tradingDTO.getNumberOfResourcesOffered();
        int numOfResourcesToGet = tradingDTO.getNumberOfResourcesToGet();

        if(resourceToOffer.equals(resourceToGet)){
            throw new BadTradingException("Trying to trade the same resource");
        }

        playerService.updateCardsAfterTradingWithPlayer(offeringPlayerId, acceptingPlayerId, resourceToOffer,
                resourceToGet, numOfResourcesToOffer, numOfResourcesToGet);

        return getGame();
    }

    public void deleteAllGames(){
        gameRepository.deleteAll();
    }
}
