package com.catan.service;

import com.catan.exceptions.PasswordIncorrectException;
import com.catan.exceptions.PlayerNotFoundException;
import com.catan.model.Player;
import com.catan.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }



    public Player getPlayerById(int id){
        Optional<Player> playerDB = playerRepository.findById(id);
        if(playerDB.isEmpty()){
            throw new PlayerNotFoundException("Player not found");
        }else{
            return playerDB.get();
        }
    }

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updateVictoryPoints(Player player, int points) {
        player.setVictoryPoints(points);
        this.playerRepository.save(player);
        return player;
    }

    public Player updatePoints(int id, int points){
        return updateVictoryPoints(this.getPlayerById(id), points);
    }
}
