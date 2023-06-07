package com.catan.model.board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class VertexTest {
    @Test
    public void testVertexInitialization() {
        // Create a sample building
        Building building = new Building();
        building.setId(1);

        // Create sample edges
        List<Edge> edges = new ArrayList<>();
        Edge edge1 = new Edge();
        edge1.setId(10);
        Edge edge2 = new Edge();
        edge2.setId(20);
        edges.add(edge1);
        edges.add(edge2);

        // Create a vertex
        Vertex vertex = new Vertex();
        vertex.setId(100);
        vertex.setBuilding(building);
        vertex.setEdges(edges);

        // Perform assertions
        Assertions.assertEquals(100, vertex.getId());
        Assertions.assertEquals(building, vertex.getBuilding());
        Assertions.assertEquals(edges, vertex.getEdges());
    }
}
