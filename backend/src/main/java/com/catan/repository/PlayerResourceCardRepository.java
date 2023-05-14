package com.catan.repository;

import com.catan.model.Player;
import com.catan.model.PlayerResourceCard;
import com.catan.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerResourceCardRepository extends JpaRepository<PlayerResourceCard, Integer> {
    List<PlayerResourceCard> findAllByPlayerId(int playerId);

    void deleteByPlayerIdAndResource(int playerId, Resource resource);

}
