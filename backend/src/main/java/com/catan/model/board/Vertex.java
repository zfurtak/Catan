package com.catan.model.board;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Vertex")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vertex {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private Building building;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "connected_edges_vertex",
               joinColumns = @JoinColumn(name = "vertex_id"),
               inverseJoinColumns = @JoinColumn(name = "field_edge_id"))
    private List<Edge> edges;
}
