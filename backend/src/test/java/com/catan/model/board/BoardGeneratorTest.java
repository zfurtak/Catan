package com.catan.model.board;

import com.catan.model.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class BoardGeneratorTest {
    @Test
    public void testGenerateFields() {
        List<Field> fields = BoardGenerator.generateFields();

        Assertions.assertEquals(19, fields.size()); // id from 0-18

        // Test a specific field (Not all LOL, too much hardcode)
        Field field = fields.get(0);
        Assertions.assertEquals(0, field.getId());
        Assertions.assertEquals(Resource.STONE, field.getResource());
        Assertions.assertEquals(10, field.getDiceNumber());
        Assertions.assertFalse(field.isBlocked());

        // Test the edges of the field
        List<Edge> edges = field.getEdges();
        Assertions.assertEquals(6, edges.size());

        Edge edge1 = edges.get(0);
        Assertions.assertNotNull(edge1);
        Assertions.assertEquals(0, edge1.getId());

        Edge edge2 = edges.get(1);
        Assertions.assertNotNull(edge2);
        Assertions.assertEquals(1, edge2.getId());

        Edge edge3 = edges.get(2);
        Assertions.assertNotNull(edge3);
        Assertions.assertEquals(2, edge3.getId());

        Edge edge4 = edges.get(3);
        Assertions.assertNotNull(edge4);
        Assertions.assertEquals(3, edge4.getId());

        Edge edge5 = edges.get(4);
        Assertions.assertNotNull(edge5);
        Assertions.assertEquals(4, edge5.getId());

        Edge edge6 = edges.get(5);
        Assertions.assertNotNull(edge6);
        Assertions.assertEquals(5, edge6.getId());
        // I could perhaps add more assertions

        // Test the vertices of the field
        List<Vertex> vertices = field.getVertices();
        Assertions.assertEquals(6, vertices.size());

        Vertex vertex1 = vertices.get(0);
        Assertions.assertNotNull(vertex1);
        Assertions.assertEquals(0, vertex1.getId());

        Vertex vertex2 = vertices.get(1);
        Assertions.assertNotNull(vertex2);
        Assertions.assertEquals(1, vertex2.getId());

        Vertex vertex3 = vertices.get(2);
        Assertions.assertNotNull(vertex3);
        Assertions.assertEquals(2, vertex3.getId());

        Vertex vertex4 = vertices.get(3);
        Assertions.assertNotNull(vertex4);
        Assertions.assertEquals(3, vertex4.getId());

        Vertex vertex5 = vertices.get(4);
        Assertions.assertNotNull(vertex5);
        Assertions.assertEquals(4, vertex5.getId());

        Vertex vertex6 = vertices.get(5);
        Assertions.assertNotNull(vertex6);
        Assertions.assertEquals(5, vertex6.getId());

        // I could Test other fields, vertices, edges, etc
        // in a similar manner, but with 1 is enough

    }
}