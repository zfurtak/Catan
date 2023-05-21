
package com.catan.service;

import com.catan.model.board.Vertex;
import com.catan.repository.VertexRepository;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Service for the Vertex class.
 */
@Service
public class VertexService {
    private final VertexRepository vertexRepository;

    /**
     * Initialize the service.
     * @param vertexRepository  the Vertex repository associated to this service
     */
    public VertexService(VertexRepository vertexRepository){
        this.vertexRepository = vertexRepository;
    }

    /**
     * Saves the specified vertex in the repository. Returns the vertex that has been saved.
     * @param vertex vertex to be saved
     * @return the saved vertex
     */
    public Vertex saveVertex(Vertex vertex){
        return vertexRepository.save(vertex);
    }

    /**
     * Returns the vertex with the specified vertex's id
     * @exception NoSuchElementException if a vertex with the specified vertex's id is not in the repository
     * @param vertex vertex whose id is used to get the wanted vertex
     * @return vertex with the provided vertex's id
     */
    public Vertex getVertex(Vertex vertex){
        Optional<Vertex> vertexDB = vertexRepository.findById(vertex.getId());
        if (vertexDB.isEmpty()) {
            throw new NoSuchElementException("Vertex not found");
        } else {
            return vertexDB.get();
        }
    }
}
