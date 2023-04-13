package com.catan.model.repository;
import com.catan.model.player.Player;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Player findByUsername(String name);
}
