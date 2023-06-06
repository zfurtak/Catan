package com.catan.repository;
import com.catan.model.Player;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository to save Player objects.
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{

    /**
     * Returns the player associated to the user with the specified id. If it is not saved in the repository it will return null.
     * @param user_id id of the user associated with the player
     * @return player if found, else null
     */
    Optional<Player> findByUserId(int user_id);


}
