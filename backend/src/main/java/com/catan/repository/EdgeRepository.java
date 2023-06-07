package com.catan.repository;


import com.catan.model.board.Edge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository to save Edge objects.
 */
public interface EdgeRepository extends JpaRepository<Edge, Integer> {

    /**
     * Returns the edge associated to the specified id. If it is not saved in the repository it will return null.
     * @param id id of the edge
     * @return edge if found, else null
     */
    Optional<Edge> findById(int id);
}
