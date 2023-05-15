package com.catan.model.board;

import com.catan.model.Resource;
import com.catan.service.VertexService;

import java.util.ArrayList;
import java.util.List;

public final class BoardGenerator {
    private static final int NUM_OF_FIELDS = 19;
    private static final int NUM_OF_EDGES = 72;
    private static final int NUM_OF_VERTICES = 54;

    private BoardGenerator(){

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
        // 0 es piedra, 1 es wool, 9 es wood, 12 es wheat, 6 es brick
        fields.add(new Field(0, Resource.STONE, 10, false, //S
                new ArrayList<>(List.of(edges.get(0), edges.get(1), edges.get(2),
                        edges.get(3), edges.get(4), edges.get(5))),
                new ArrayList<>(List.of(vertices.get(0), vertices.get(1), vertices.get(2),
                        vertices.get(3), vertices.get(4), vertices.get(5)))));
        // Lo que he toqueteado es de aqui para abajo
        // here create every field like the example above
        fields.add(new Field(1, Resource.WOOL, 2, false, //S
                new ArrayList<>(List.of(edges.get(6), edges.get(7), edges.get(8),
                        edges.get(9), edges.get(10), edges.get(2))),
                new ArrayList<>(List.of(vertices.get(6), vertices.get(7), vertices.get(8),
                        vertices.get(9), vertices.get(2), vertices.get(1)))));
        // WOOD, ID = 2, DICE = 9
        fields.add(new Field(2, Resource.WOOD, 9, false, //S
                new ArrayList<>(List.of(edges.get(11), edges.get(12), edges.get(13),
                        edges.get(14), edges.get(15), edges.get(8))),
                new ArrayList<>(List.of(vertices.get(10), vertices.get(11), vertices.get(12),
                        vertices.get(13), vertices.get(8), vertices.get(7)))));

        fields.add(new Field(3, Resource.WHEAT, 12, false, //S
                new ArrayList<>(List.of(edges.get(16), edges.get(4), edges.get(17),
                        edges.get(18), edges.get(19), edges.get(20))),
                new ArrayList<>(List.of(vertices.get(4), vertices.get(3), vertices.get(14),
                        vertices.get(15), vertices.get(16), vertices.get(17)))));

        fields.add(new Field(4, Resource.BRICK, 6, false, //S
                new ArrayList<>(List.of(edges.get(3), edges.get(10), edges.get(21),
                        edges.get(22), edges.get(23), edges.get(17))),
                new ArrayList<>(List.of(vertices.get(2), vertices.get(9), vertices.get(18),
                        vertices.get(19), vertices.get(14), vertices.get(3)))));

        fields.add(new Field(5, Resource.WOOL, 4, false, //S
                new ArrayList<>(List.of(edges.get(9), edges.get(15), edges.get(24),
                        edges.get(25), edges.get(26), edges.get(21))),
                new ArrayList<>(List.of(vertices.get(8), vertices.get(13), vertices.get(20),
                        vertices.get(21), vertices.get(18), vertices.get(9)))));

        fields.add(new Field(6, Resource.BRICK, 10, false, //S
                new ArrayList<>(List.of(edges.get(14), edges.get(27), edges.get(28),
                        edges.get(29), edges.get(30), edges.get(24))),
                new ArrayList<>(List.of(vertices.get(12), vertices.get(22), vertices.get(23),
                        vertices.get(32), vertices.get(20), vertices.get(13)))));

        fields.add(new Field(7, Resource.WHEAT, 9, false, //S
                new ArrayList<>(List.of(edges.get(31), edges.get(19), edges.get(32),
                        edges.get(33), edges.get(34), edges.get(35))),
                new ArrayList<>(List.of(vertices.get(16), vertices.get(15), vertices.get(24),
                        vertices.get(25), vertices.get(26), vertices.get(27)))));

        fields.add(new Field(8, Resource.WOOD, 11, false, //S
                new ArrayList<>(List.of(edges.get(18), edges.get(23), edges.get(36),
                        edges.get(37), edges.get(48), edges.get(32))),
                new ArrayList<>(List.of(vertices.get(14), vertices.get(19), vertices.get(28),
                        vertices.get(29), vertices.get(24), vertices.get(15)))));
        // Dessert blocked in the beginning
        fields.add(new Field(9, Resource.DESSERT, -1, true, //S
                new ArrayList<>(List.of(edges.get(22), edges.get(26), edges.get(38),
                        edges.get(39), edges.get(40), edges.get(36))),
                new ArrayList<>(List.of(vertices.get(18), vertices.get(21), vertices.get(30),
                        vertices.get(31), vertices.get(28), vertices.get(19)))));

        fields.add(new Field(10, Resource.WOOD, 3, false, //S
                new ArrayList<>(List.of(edges.get(25), edges.get(30), edges.get(41),
                        edges.get(42), edges.get(43), edges.get(38))),
                new ArrayList<>(List.of(vertices.get(20), vertices.get(32), vertices.get(33),
                        vertices.get(34), vertices.get(30), vertices.get(21)))));

        fields.add(new Field(11, Resource.STONE, 8, false, //S
                new ArrayList<>(List.of(edges.get(29), edges.get(44), edges.get(45),
                        edges.get(46), edges.get(47), edges.get(41))),
                new ArrayList<>(List.of(vertices.get(23), vertices.get(35), vertices.get(36),
                        vertices.get(37), vertices.get(33), vertices.get(32)))));

        fields.add(new Field(12, Resource.WOOD, 8, false, //S
                new ArrayList<>(List.of(edges.get(33), edges.get(48), edges.get(49),
                        edges.get(50), edges.get(51), edges.get(52))),
                new ArrayList<>(List.of(vertices.get(24), vertices.get(29), vertices.get(38),
                        vertices.get(39), vertices.get(40), vertices.get(25)))));

        fields.add(new Field(13, Resource.STONE, 3, false, //S
                new ArrayList<>(List.of(edges.get(37), edges.get(40), edges.get(53),
                        edges.get(54), edges.get(55), edges.get(49))),
                new ArrayList<>(List.of(vertices.get(28), vertices.get(31), vertices.get(41),
                        vertices.get(42), vertices.get(38), vertices.get(29)))));

        fields.add(new Field(14, Resource.WHEAT, 4, false, //S
                new ArrayList<>(List.of(edges.get(39), edges.get(43), edges.get(56),
                        edges.get(57), edges.get(58), edges.get(53))),
                new ArrayList<>(List.of(vertices.get(30), vertices.get(34), vertices.get(43),
                        vertices.get(44), vertices.get(41), vertices.get(31)))));

        fields.add(new Field(15, Resource.WOOL, 5, false, // S
                new ArrayList<>(List.of(edges.get(42), edges.get(47), edges.get(59),
                        edges.get(60), edges.get(61), edges.get(56))),
                new ArrayList<>(List.of(vertices.get(33), vertices.get(37), vertices.get(45),
                        vertices.get(46), vertices.get(43), vertices.get(34)))));

        fields.add(new Field(16, Resource.BRICK, 5, false, //S
                new ArrayList<>(List.of(edges.get(50), edges.get(55), edges.get(62),
                        edges.get(63), edges.get(64), edges.get(65))),
                new ArrayList<>(List.of(vertices.get(38), vertices.get(42), vertices.get(47),
                        vertices.get(48), vertices.get(49), vertices.get(39)))));

        fields.add(new Field(17, Resource.WHEAT, 6, false, //S
                new ArrayList<>(List.of(edges.get(53), edges.get(58), edges.get(66),
                        edges.get(67), edges.get(68), edges.get(62))),
                new ArrayList<>(List.of(vertices.get(41), vertices.get(44), vertices.get(50),
                        vertices.get(51), vertices.get(47), vertices.get(42)))));

        fields.add(new Field(18, Resource.WOOL, 11, false, //S
                new ArrayList<>(List.of(edges.get(57), edges.get(61), edges.get(69),
                        edges.get(70), edges.get(61), edges.get(66))), //S
                new ArrayList<>(List.of(vertices.get(43), vertices.get(46), vertices.get(52),
                        vertices.get(53), vertices.get(50), vertices.get(44)))));

        // return is ok

        return fields;
    }
}
