package com.catan.model.service;

import com.catan.model.player.Player;
import com.catan.model.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Override
    public Optional<Player> getSpecificPlayer(String username) {
        return Optional.of(playerRepository.findByUsername(username));
    }

    @Override
    public void addPlayer(Player player) {
        playerRepository.save(player);
    }

    @Override
    public void updatePoints(Player player) {
        if(playerRepository.findById(player.getId()).isPresent()){
            Player playerDB = playerRepository.findById(player.getId()).get();
            playerDB.setPoints(player.getPoints());
        }
    }
}
