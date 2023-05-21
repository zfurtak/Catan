package com.catan.repository;

import com.catan.model.Player;
import com.catan.model.PlayerResourceCard;
import com.catan.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerResourceCardRepository extends JpaRepository<PlayerResourceCard, Integer> {
    /**
     * Returns a list with all the resource cards from the player with the specified id.
     * @param playerId id of the player whose cards are got
     * @return  list with all the resource cards from the player
     */
    List<PlayerResourceCard> findAllByPlayerId(int playerId);

    /**
     * Deletes one unit of the specified resource type from the player with the specified id.
     * @param playerId id of the player whose resource is being deleted
     * @param resource resource type to be deleted
     */
    void deleteByPlayerIdAndResource(int playerId, Resource resource);

}
