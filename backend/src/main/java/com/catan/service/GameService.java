package com.catan.service;

import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void deleteGameById(int id){
        gameRepository.deleteById(id);
    }

}
