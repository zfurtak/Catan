package com.catan.model.board;

import com.catan.model.Resource;
import com.catan.service.VertexService;

import java.util.ArrayList;
import java.util.List;

public final class BoardGenerator {
    private static final int NUM_OF_FIELDS = 19;
    private static final int NUM_OF_EDGES = 72;
    private static final int NUM_OF_VERTICES = 54;

    public BoardGenerator(){

    }

    public static List<Field> generateFields(){
        List<Field> fields = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        List<Vertex> vertices = new ArrayList<>();

        for(int i=0; i < NUM_OF_VERTICES; i++){
            vertices.add(new Vertex(i, null));
        }

        for(int i = 0; i < NUM_OF_EDGES; i++){
            edges.add(new Edge(i, true, null));
        }


        fields.add(new Field(0, Resource.STONE, 10, false,
                new ArrayList<>(List.of(edges.get(0), edges.get(1), edges.get(2),
                        edges.get(3), edges.get(4), edges.get(5))),
                new ArrayList<>(List.of(vertices.get(0), vertices.get(1), vertices.get(2),
                        vertices.get(3), vertices.get(4), vertices.get(5)))));
        // Lo que he toqueteado es de aqui para abajo
        // here create every field like the example above
        fields.add(new Field(1, Resource.WHEAT, 2, false,
                new ArrayList<>(List.of(edges.get(0), edges.get(1), edges.get(2),
                        edges.get(3), edges.get(4), edges.get(5))),
                new ArrayList<>(List.of(vertices.get(0), vertices.get(1), vertices.get(2),
                        vertices.get(3), vertices.get(4), vertices.get(5)))));

        fields.add(new Field(2, Resource.DESSERT, 3, false,
                new ArrayList<>(List.of(edges.get(0), edges.get(1), edges.get(2),
                        edges.get(3), edges.get(4), edges.get(5))),
                new ArrayList<>(List.of(vertices.get(0), vertices.get(1), vertices.get(2),
                        vertices.get(3), vertices.get(4), vertices.get(5)))));

        fields.add(new Field(3, Resource.WOOL, 5, false,
                new ArrayList<>(List.of(edges.get(0), edges.get(1), edges.get(2),
                        edges.get(3), edges.get(4), edges.get(5))),
                new ArrayList<>(List.of(vertices.get(0), vertices.get(1), vertices.get(2),
                        vertices.get(3), vertices.get(4), vertices.get(5)))));

        fields.add(new Field(4, Resource.BRICK, 4, false,
                new ArrayList<>(List.of(edges.get(0), edges.get(1), edges.get(2),
                        edges.get(3), edges.get(4), edges.get(5))),
                new ArrayList<>(List.of(vertices.get(0), vertices.get(1), vertices.get(2),
                        vertices.get(3), vertices.get(4), vertices.get(5)))));

        fields.add(new Field(5, Resource.WOOD, 8, false,
                new ArrayList<>(List.of(edges.get(0), edges.get(1), edges.get(2),
                        edges.get(3), edges.get(4), edges.get(5))),
                new ArrayList<>(List.of(vertices.get(0), vertices.get(1), vertices.get(2),
                        vertices.get(3), vertices.get(4), vertices.get(5)))));







        // return is ok

        return fields;
    }
}
