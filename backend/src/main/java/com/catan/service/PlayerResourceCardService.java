package com.catan.service;

import com.catan.exceptions.ResourceCardNotFoundException;
import com.catan.model.Player;
import com.catan.model.PlayerResourceCard;
import com.catan.model.Resource;
import com.catan.repository.PlayerResourceCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * @exception ResourceCardNotFoundException if the player with the specified id does not have cards saved in the repository
     * or does not have cards of the specified resource type
     * @param playerId id of the player whose resource is being deleted
     * @param resource resource type to be deleted
     * @author Minerva Gomez
     */
    public void deleteByPlayerIdAndResource(int playerId, Resource resource){
        List <PlayerResourceCard> resources = this.findAllCardsByPlayerId(playerId);
        PlayerResourceCard resourceToErase = null;
        int i = 0;                                                     
        while(i < resources.size() && resourceToErase == null){        //we look for a card of the resourcetype among the player's saved cards
            PlayerResourceCard card = resources.get(i);
            if(card.getResource() == resource){
                resourceToErase  = card;
            }
            i++;
        }
        if(resourceToErase != null){
            this.deleteById(resourceToErase.getId());
        } else{
            throw new ResourceCardNotFoundException("Trying to delete a card of a resource type the player does not posses.");
        }
    }

    public List<PlayerResourceCard> getPlayersCardNumber(Player player) {
        return playerResourceCardRepository.findAllByPlayerId(player.getId());
    }

    /**
     * Deletes the resource card from the repository with the specified id.
     * @exception ResourceCardNotFoundException if the card with the specified id is not saved in the repository
     * @param id id from the card to be deleted
     * @author Minerva Gomez
     */
    public void deleteById(int id) {
        int pre = this.playerResourceCardRepository.findAll().size();
        this.playerResourceCardRepository.deleteById(id);
        int post = this.playerResourceCardRepository.findAll().size();
        if(pre == post){
            throw new ResourceCardNotFoundException("Trying to erase a non-existing resource card.");
        }
    }
}
