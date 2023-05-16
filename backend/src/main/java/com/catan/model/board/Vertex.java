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

    // TODO: czy rozpatrujemy wierzchołki jako jeden dla kilku fieldów czy każdy field ma swoje 6 wierzchołkow?
    @ManyToOne
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private Building building;

    @ManyToMany
    @JoinTable()
    private List<Edge> edges;
}
