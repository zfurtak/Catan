package com.catan.repository;

import com.catan.model.PlayerResourceCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerResourceCardRepository extends JpaRepository<PlayerResourceCard, Integer> {
    @Query(value = "SELECT * FROM PlayerResourceCard r WHERE r.player_id=?1", nativeQuery = true)
    List<PlayerResourceCard> findByPlayerIDAllResourceCards(int playerId);

}
