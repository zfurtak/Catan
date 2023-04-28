package com.catan.service;

import com.catan.exceptions.GameNotFoundException;
import com.catan.exceptions.TooManyPlayersException;
import com.catan.model.board.BoardGenerator;
import com.catan.model.board.Field;
import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.model.User;
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
                       UserService userService){
        this.gameRepository = gameRepository;
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
        }else{
            return games.get(0);
        }
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
    public Game thief(int userId){
        Game game = this.getGame();
        //  .....
        return game;
    }
}
