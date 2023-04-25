package com.catan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "BoardField")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Resource resource;
    private int diceNumber;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "field_edge_id", referencedColumnName = "id")
    private List<FieldEdge> fieldEdges;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "field_vertex_id", referencedColumnName = "id")
    private List<FieldVertex> fieldVertices;
}
