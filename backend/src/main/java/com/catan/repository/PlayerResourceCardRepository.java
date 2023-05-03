package com.catan.repository;

import com.catan.model.PlayerResourceCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerResourceCardRepository extends JpaRepository<PlayerResourceCard, Integer> {
    List<PlayerResourceCard> findAllByPlayerId(int playerId);

}
