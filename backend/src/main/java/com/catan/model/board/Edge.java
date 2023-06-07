package com.catan.model.board;

import com.catan.model.Color;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Edge")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Edge {
    @Id
    private int id;

    @OneToOne
    @JoinColumn(name = "road_id", referencedColumnName = "id")
    private Road road;

    @JsonIgnore
    @ManyToMany(mappedBy = "edges")
    List<Vertex> vertices;

    Color colorOfEdge;

    public Color getColorOfEdge() {
        if (this.getRoad() != null) return this.getRoad().getPlayer().getColor();
        return null;
    }
}
