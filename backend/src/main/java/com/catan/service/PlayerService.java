package com.catan.service;

import com.catan.exceptions.UserNotFoundException;
import com.catan.model.*;
import com.catan.repository.PlayerRepository;
import com.catan.repository.PlayerResourceCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerResourceCardRepository playerResourceCardRepository;


    @Autowired
    public PlayerService(PlayerRepository playerRepository, PlayerResourceCardRepository playerResourceCardRepository){
        this.playerRepository = playerRepository;
        this.playerResourceCardRepository = playerResourceCardRepository;
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

    public Map<Resource, Integer> getPlayerResources(int playerId){
        List<PlayerResourceCard> resourceCards = playerResourceCardRepository.findAllByPlayerId(playerId);
        Map<Resource, Integer> playerResources = new HashMap<>();
        for (PlayerResourceCard resource : resourceCards){
            if(playerResources.containsKey(resource.getResource())){
                playerResources.put(resource.getResource(), playerResources.get(resource.getResource())+1);
            } else {
                playerResources.put(resource.getResource(), 1);
            }
        }
        //        Map<Resource, Integer> playerResources = resourceCards.stream()
        //                .collect(Collectors.toMap(PlayerResourceCard::getResource, PlayerResourceCard::getId));
        return playerResources;
    }

    public Player updateVictoryPoints(int id, int points){
        return updateVictoryPoints(this.getPlayerById(id), points);
    }

    public void deletePlayers(){
        playerRepository.deleteAll();
    }
}
