package com.catan.service;

import com.catan.exceptions.GameNotFoundException;
import com.catan.exceptions.TooManyPlayersException;
import com.catan.model.*;
import com.catan.model.board.BoardGenerator;
import com.catan.model.board.Field;
import com.catan.repository.GameRepository;
import com.catan.repository.PlayerResourceCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerResourceCardRepository playerResourceCardRepository;
    private final PlayerService playerService;
    private final UserService userService;

    @Autowired
    public GameService(GameRepository gameRepository,
                       PlayerResourceCardRepository playerResourceCardRepository,
                       PlayerService playerService,
                       UserService userService){
        this.gameRepository = gameRepository;
        this.playerResourceCardRepository = playerResourceCardRepository;
        this.playerService = playerService;
        this.userService = userService;
    }

    public Game joinGame(int userId){
        User user = userService.getUserById(userId);
        Player player = playerService.getPlayerByUserId(userId);
        player.setUser(user);
        List<Game> games = gameRepository.findAll();

        if(games.isEmpty()){
            return createGame(player);
        }else{
            Game game = games.get(0);
            return this.joinExistingGame(game, player);
        }
    }

    public Game getGame(){
        List<Game> games = gameRepository.findAll();

        if(games.isEmpty()){
            throw new GameNotFoundException();
        } else{
            return games.get(0);
        }
    }

    // TODO: change return value to a Map
    public List<Resource> getPlayerResources(int playerId){
        List<Resource> resources = new ArrayList<>();
        List<PlayerResourceCard> resourceCards = playerResourceCardRepository.findByPlayerIDAllResourceCards(playerId);
        for (PlayerResourceCard r : resourceCards){
            resources.add(r.getResource());
        }
        return resources;
    }

    //TODO: create fields + set good diceNumber to good field (int whichField)
    // BoardGenerator.java
    public Game createGame(Player initialPlayer){
        List<Player> players = new ArrayList<>();
        List<Field> fields = BoardGenerator.generateFields();
        players.add(initialPlayer);
        Game newGame = new Game(players, fields);
        return gameRepository.save(newGame);
    }

    public Game joinExistingGame(Game game, Player newPlayer){
        if(game.getPlayers().size() < 4){
            game.getPlayers().add(newPlayer);
            return gameRepository.save(game);
        }else{
            throw new TooManyPlayersException("They are already 4 players");
        }
    }

    public void deleteGame(int id){
        gameRepository.deleteAll();
        // not sure if it is needed (Cascade = Cascade.ALL)
        playerService.deletePlayers();
    }

    // TODO : everything that is happening connected to the thief
    public Game thief(int userId, Field field){
        Game game = this.getGame();

        for (Player player : game.getPlayers()){
            // check if this is the correct id, in player table we have two different ids rn
            List<PlayerResourceCard> playerCards = playerResourceCardRepository.findByPlayerIDAllResourceCards(player.getId());
            if(playerCards.size() > 7) {
                // for now we randomly remove half of player cards
                int numberOfCardsToRemove = playerCards.size()/2;
                while (numberOfCardsToRemove > 0){
                    int idToRemove = playerCards.get(0).getId();
                    playerResourceCardRepository.deleteById(idToRemove);
                    numberOfCardsToRemove--;
                }
            }
        }

        // TODO: add query to get id of the blocked field (previous field with thief)

        // TODO: put thief on the field chosen by player
        field.setBlocked(true);

        return game;
    }
}
