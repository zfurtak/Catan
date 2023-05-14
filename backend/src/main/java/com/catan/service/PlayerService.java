package com.catan.service;

import com.catan.exceptions.UserNotFoundException;
import com.catan.model.*;
import com.catan.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerResourceCardService playerResourceCardService;


    @Autowired
    public PlayerService(PlayerRepository playerRepository,
                         PlayerResourceCardService playerResourceCardService) {
        this.playerRepository = playerRepository;
        this.playerResourceCardService = playerResourceCardService;
    }

    public Player getPlayerById(int id) {
        Optional<Player> playerDB = playerRepository.findById(id);
        if (playerDB.isEmpty()) {
            throw new UserNotFoundException("Player not found");
        } else {
            return playerDB.get();
        }
    }

    public Player getPlayerByUserId(int userId) {
        Optional<Player> playerDB = playerRepository.findByUserId(userId);
        if (playerDB.isEmpty()) {
            throw new UserNotFoundException("Player not found");
        } else {
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

    public Map<Resource, Integer> getPlayerResources(int playerId) {
        List<PlayerResourceCard> resourceCards = playerResourceCardService.findAllCardsByPlayerId(playerId);
        Map<Resource, Integer> playerResources = new HashMap<>();
        for (PlayerResourceCard resourceCard : resourceCards) {
            Resource resource = resourceCard.getResource();
            if (playerResources.containsKey(resource)) {
                playerResources.put(resource, playerResources.get(resource) + 1);
            } else {
                playerResources.put(resource, 1);
            }
        }
        return playerResources;
    }

    public List<Integer> getResourcesToTradeWithBank(int playerId){
        return this.getPlayerResources(playerId).entrySet()
                .stream()
                    .filter(x -> x.getValue() >= 4)
                    .map(x -> x.getKey().ordinal())
                        .toList();
    }

    public void updateCardsAfterTradingWithBank(int playerId, Resource resourceFromPlayer, Resource resourceFromBank){
        for(int i = 0; i < 4; i++)
            playerResourceCardService.deleteByPlayerIdAndResource(playerId, resourceFromPlayer);
        Player playerDB = this.getPlayerById(playerId);
        playerResourceCardService.addCard(playerDB, resourceFromBank);
    }

    public Player updateVictoryPoints(int id, int points) {
        return updateVictoryPoints(this.getPlayerById(id), points);
    }

    public void deletePlayers() {
        playerRepository.deleteAll();
    }
}
