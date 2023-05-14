package com.catan.service;

import com.catan.model.board.Field;
import com.catan.model.board.Vertex;
import com.catan.model.board.Building;
import com.catan.repository.VertexRepository;
import org.springframework.stereotype.Service;


@Service
public class VertexService {
    private final VertexRepository vertexRepository;


    public VertexService(VertexRepository vertexRepository){
        this.vertexRepository = vertexRepository;
    }

    public Vertex saveVertex(Vertex vertex){
        return vertexRepository.save(vertex);
    }

}
