package com.catan.service;

import com.catan.model.board.Vertex;
import com.catan.repository.VertexRepository;
import com.catan.service.VertexService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.NoSuchElementException;
import java.util.Optional;

public class VertexServiceTest {

    private VertexRepository vertexRepository;
    private VertexService vertexService;

    @BeforeEach
    public void setup() {
        vertexRepository = Mockito.mock(VertexRepository.class);
        vertexService = new VertexService(vertexRepository);
    }

    @Test
    public void testSaveVertex() {
        Vertex vertex = new Vertex();
        vertex.setId(1);
        Mockito.when(vertexRepository.save(vertex)).thenReturn(vertex);

        Vertex savedVertex = vertexService.saveVertex(vertex);

        Assertions.assertEquals(vertex, savedVertex);
        Mockito.verify(vertexRepository, Mockito.times(1)).save(vertex);
    }

    @Test
    public void testGetVertex() {
        Vertex vertex = new Vertex();
        vertex.setId(1);
        Optional<Vertex> optionalVertex = Optional.of(vertex);
        Mockito.when(vertexRepository.findById(vertex.getId())).thenReturn(optionalVertex);

        Vertex retrievedVertex = vertexService.getVertex(vertex);

        Assertions.assertEquals(vertex, retrievedVertex);
        Mockito.verify(vertexRepository, Mockito.times(1)).findById(vertex.getId());
    }

    @Test
    public void testGetVertexNotFound() {
        Vertex vertex = new Vertex();
        vertex.setId(1);
        Optional<Vertex> optionalVertex = Optional.empty();
        Mockito.when(vertexRepository.findById(vertex.getId())).thenReturn(optionalVertex);

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            vertexService.getVertex(vertex);
        });

        Mockito.verify(vertexRepository, Mockito.times(1)).findById(vertex.getId());
    }
}