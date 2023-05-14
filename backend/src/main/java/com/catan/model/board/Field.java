package com.catan.model.board;

import com.catan.model.Resource;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Field")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Field {
    @Id
    private int id;
    private Resource resource;
    private int diceNumber;
    private boolean isBlocked;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "field_edge_id", referencedColumnName = "id")
    private List<Edge> edges;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "vertex_id", referencedColumnName = "id")
    private List<Vertex> vertices;
}
