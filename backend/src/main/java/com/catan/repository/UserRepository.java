package com.catan.repository;

import com.catan.model.Player;
import com.catan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Returns the user with the specified username. If it is not saved in the repository it will return null.
     * @param name username of the user to be found
     * @return user if found, else null
     */
    Optional<User> findByUsername(String name);
}
