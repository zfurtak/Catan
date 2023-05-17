package com.catan.repository;


import com.catan.model.board.Edge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EdgeRepository extends JpaRepository<Edge, Integer> {

    Optional<Edge> findById(int id);
}
