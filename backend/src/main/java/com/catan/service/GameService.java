package com.catan.service;

import com.catan.exceptions.TooManyPlayersException;
import com.catan.exceptions.UserNotFoundException;
import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public Game createGame(Player initialPlayer){
        Game newGame = new Game();
        List<Player> players = new ArrayList<>();
        players.add(initialPlayer);
        newGame.setPlayers(players);
        return gameRepository.save(newGame);
    }

    public Game joinNewPlayer(int id, Player newPlayer){
        Optional<Game> gameDB = gameRepository.findById(id);
        if(gameDB.isEmpty()){
            throw new NullPointerException("Game not found");
        }else{
            Game game = gameDB.get();
            if(game.getPlayers().size() < 4){
                game.getPlayers().add(newPlayer);
                return gameRepository.save(game);
            }else{
                throw new TooManyPlayersException("They are already 4 players");
            }

        }
    }

    public void deleteGameById(int id){
        gameRepository.deleteById(id);
    }

}
