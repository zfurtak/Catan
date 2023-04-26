package com.catan.service;

import com.catan.exceptions.TooManyPlayersException;
import com.catan.exceptions.UserNotFoundException;
import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.model.User;
import com.catan.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final UserService userService;

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

    public Game createGame(Player initialPlayer){
        Game newGame = new Game();
        List<Player> players = new ArrayList<>();
        players.add(initialPlayer);
        newGame.setPlayers(players);
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

    public void deleteGameById(int id){
        gameRepository.deleteById(id);
    }

}
