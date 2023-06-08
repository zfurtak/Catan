package com.catan.model.board;

import com.catan.model.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to test the Field class
 * @author Ivan Iroslavov
 */
class FieldTest {
    @Test
    public void testFieldInitialization() {
        // Create sample resource and edges
        Resource resource = Resource.WOOD;
        List<Edge> edges = new ArrayList<>();

        // Create sample vertices
        List<Vertex> vertices = new ArrayList<>();
        Vertex vertex1 = new Vertex();
        vertex1.setId(100);
        Vertex vertex2 = new Vertex();
        vertex2.setId(200);
        vertices.add(vertex1);
        vertices.add(vertex2);

        // Create a field
        Field field = new Field();
        field.setId(1);
        field.setResource(resource);
        field.setDiceNumber(6);
        field.setBlocked(false);
        field.setEdges(edges);
        field.setVertices(vertices);

        // Perform assertions
        Assertions.assertEquals(1, field.getId());
        Assertions.assertEquals(resource, field.getResource());
        Assertions.assertEquals(6, field.getDiceNumber());
        Assertions.assertFalse(field.isBlocked());
        Assertions.assertEquals(edges, field.getEdges());
        Assertions.assertEquals(vertices, field.getVertices());
    }
}