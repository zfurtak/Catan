package com.catan.model.service;

import com.catan.model.exceptions.PasswordIncorrectException;
import com.catan.model.exceptions.PlayerNotFoundException;
import com.catan.model.player.Player;
import com.catan.model.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Override
    public Player getPlayerByName(String username) {
        Optional<Player> playerDB = playerRepository.findByUsername(username);
        if(playerDB.isEmpty()){
            throw new PlayerNotFoundException("Player not found");
        }else{
            return playerDB.get();
        }
    }

    @Override
    public Player getPlayerById(int id){
        Optional<Player> playerDB = playerRepository.findById(id);
        if(playerDB.isEmpty()){
            throw new PlayerNotFoundException("Player not found");
        }else{
            return playerDB.get();
        }
    }

    @Override
    public Player logPlayerIn(String username, String password){
        Player playerDB = this.getPlayerByName(username);
        if(password.equals(playerDB.getPassword())){
            return playerDB;
        }else{
            throw new PasswordIncorrectException("Password is incorrect");
        }
    }

    @Override
    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player updatePoints(Player player, int points) {
        player.setPoints(points);
        this.playerRepository.save(player);
        return player;
    }

    @Override
    public Player updatePoints(int id, int points){
        return updatePoints(this.getPlayerById(id), points);
    }
}
