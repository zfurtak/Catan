package com.catan.service;

import com.catan.exceptions.IncorrectGamesNumberException;
import com.catan.exceptions.UserAlreadyExistsException;
import com.catan.exceptions.UserNotFoundException;
import com.catan.model.Color;
import com.catan.model.Player;
import com.catan.model.PlayerResourceCard;
import com.catan.model.Resource;
import com.catan.model.User;
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

    public Player createPlayer(User user, Color color) {
        Optional<Player> playerDB = playerRepository.findByUserId(user.getId());
        if (playerDB.isPresent()) {
            //TODO add proper exception
            throw new UserAlreadyExistsException("Player is already created");
        } else {
            Player p = new Player();
            p.setColor(color);
            p.setUser(user);
            return p;
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

    public void updateCardsAfterTradingWithPlayer(int offeringPlayerId, int acceptingPlayerId, Resource resourceOffered,
                                                  Resource resourceToGet, int numResourcesOffered, int numResourcesToGet){
        Player offeringPlayerDB = getPlayerById(offeringPlayerId);
        Player acceptingPlayerDB = getPlayerById(acceptingPlayerId);
        for (int i=0; i<numResourcesOffered; i++){
            playerResourceCardService.deleteByPlayerIdAndResource(offeringPlayerId, resourceOffered);
            playerResourceCardService.addCard(acceptingPlayerDB, resourceOffered);
        }
        for (int i=0; i<numResourcesToGet; i++){
            playerResourceCardService.deleteByPlayerIdAndResource(acceptingPlayerId, resourceToGet);
            playerResourceCardService.addCard(offeringPlayerDB, resourceToGet);
        }
    }

    public void deletePlayers() {
        playerRepository.deleteAll();
    }

    public Color getColor(int size){
        return switch (size) {
            case 0 -> Color.RED;
            case 1 -> Color.BLUE;
            case 2 -> Color.WHITE;
            case 3 -> Color.YELLOW;
            default -> throw new IncorrectGamesNumberException("Problem with number of games in choosing color");
        };
    }
}
