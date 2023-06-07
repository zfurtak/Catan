package com.catan.repository;

import com.catan.model.Game;
import com.catan.service.GameService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to save Game objects.
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

}
