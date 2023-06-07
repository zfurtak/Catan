package com.catan.service;

import com.catan.exceptions.UserNotFoundException;
import com.catan.model.Player;
import com.catan.model.board.Edge;
import com.catan.repository.EdgeRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Service for the Edge class.
 */
@Service
public class EdgeService {

    private final EdgeRepository edgeRepository;

    /**
     * Initialize the service.
     * @param edgeRepository the Edge repository associated to this service
     */
    public EdgeService(EdgeRepository edgeRepository){
        this.edgeRepository = edgeRepository;
    }

    /**
     * Returns the edge with the specified id.
     * @exception NoSuchElementException if an edge with the specified id is not saved in the repository
     * @param edgeId id of the edge to retrieve
     * @return edge with the given id
     */
    public Edge getEdge(int edgeId){
        Optional<Edge> edgeDB = edgeRepository.findById(edgeId);
        if (edgeDB.isEmpty()) {
            throw new NoSuchElementException("Edge not found");
        } else {
            return edgeDB.get();
        }
    }

    /**
     * Saves in the repository the specified edge.
     * @param edge edge to be saved
     */
    public void updateEdge(Edge edge){
        edgeRepository.save(edge);
    }
}
