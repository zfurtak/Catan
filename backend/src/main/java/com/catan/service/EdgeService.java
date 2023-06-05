package com.catan.service;

import com.catan.exceptions.UserNotFoundException;
import com.catan.model.Color;
import com.catan.model.Player;
import com.catan.model.board.Edge;
import com.catan.model.board.Field;
import com.catan.repository.EdgeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EdgeService {

    private final EdgeRepository edgeRepository;

    public EdgeService(EdgeRepository edgeRepository){
        this.edgeRepository = edgeRepository;
    }

    public Edge getEdge(int edgeId){
        Optional<Edge> edgeDB = edgeRepository.findById(edgeId);
        if(edgeDB.isEmpty()) {
            throw new NoSuchElementException("Edge not found");
        } else {
            return edgeDB.get();
        }
    }

    public void updateEdge(Edge edge){
        edgeRepository.save(edge);
    }

    public List<Edge> findAllByColorOfEdge(Color color) {
        return edgeRepository.findAllByColorOfEdge(color);
    }
}
