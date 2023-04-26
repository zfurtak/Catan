package com.catan.repository;
import com.catan.model.Player;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{

    public Optional<Player> findByUserId(int user_id);
}