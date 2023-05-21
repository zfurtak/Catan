package com.catan.service;

import com.catan.model.Player;
import com.catan.model.PlayerResourceCard;
import com.catan.model.Resource;
import com.catan.repository.PlayerResourceCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for the PlayerResourceCard class
 */
@Service
public class PlayerResourceCardService {
    private final PlayerResourceCardRepository playerResourceCardRepository;

    /**
     * Initialize the service.
     * @param playerResourceCardRepository the PlayerResourceCard repository associated to this service
     */
    public PlayerResourceCardService(PlayerResourceCardRepository playerResourceCardRepository) {
        this.playerResourceCardRepository = playerResourceCardRepository;
    }

    /**
     * Adds one unit of the specified resource type to the specified player.
     * @param player player who gets the resource
     * @param resource resource type to be added
     */
    public void addCard(Player player, Resource resource) {
        PlayerResourceCard playerResourceCard = new PlayerResourceCard(player, resource);
        this.playerResourceCardRepository.save(playerResourceCard);
    }

    /**
     * Returns a list with all the resource cards from the player with the specified id.
     * @param playerId id of the player whose cards are got
     * @return list with all the player's resource cards
     */
    public List<PlayerResourceCard> findAllCardsByPlayerId(int playerId){
        return playerResourceCardRepository.findAllByPlayerId(playerId);
    }

    /**
     * Deletes one unit of the specified resource type from the player with the specified id.
     * @param playerId id of the player whose resource is being deleted
     * @param resource resource type to be deleted
     */
    public void deleteResourceFromPlayer(int playerId, Resource resource){
        this.playerResourceCardRepository.deleteByPlayerIdAndResource(playerId, resource);
    }

    /**
     * Deletes the resource card from the repository with the specified id.
     * @param id id from the card to be deleted
     */
    public void deleteById(int id) {
        this.playerResourceCardRepository.deleteById(id);
    }
}
