package com.catan.repository;

import com.catan.model.board.Vertex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository to save Vertex objects.
 */
public interface VertexRepository extends JpaRepository<Vertex, Integer> {

}
