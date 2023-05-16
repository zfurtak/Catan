package com.catan.service;

import com.catan.exceptions.UserNotFoundException;
import com.catan.model.board.Edge;
import com.catan.model.board.Field;
import com.catan.model.board.Vertex;
import com.catan.model.board.Building;
import com.catan.repository.VertexRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class VertexService {
    private final VertexRepository vertexRepository;


    public VertexService(VertexRepository vertexRepository){
        this.vertexRepository = vertexRepository;
    }

    public Vertex saveVertex(Vertex vertex){
        return vertexRepository.save(vertex);
    }

    public Vertex getVertex(Vertex vertex){
        Optional<Vertex> vertexDB = vertexRepository.findById(vertex.getId());
        if (vertexDB.isEmpty()) {
            throw new NoSuchElementException("Vertex not found");
        } else {
            return vertexDB.get();
        }
    }

}
