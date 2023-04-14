package com.catan.model.repository;
import com.catan.model.player.Player;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Optional<Player> findByUsername(String name);
}
