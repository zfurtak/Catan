package com.catan.service;

import com.catan.exceptions.PlayerAlreadyExistsExceptions;
import com.catan.exceptions.UserNotFoundException;
import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.model.User;
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
            throw new UserNotFoundException("Player not found");
        }else{
            return playerDB.get();
        }
    }

    public Player getPlayerByUserId(int userId){
        Optional<Player> playerDB = playerRepository.findByUserId(userId);
        if(playerDB.isEmpty()){
            throw new UserNotFoundException("Player not found");
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

    public Player updateVictoryPoints(int id, int points){
        return updateVictoryPoints(this.getPlayerById(id), points);
    }
}
