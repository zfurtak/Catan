package com.catan.model.board;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FieldEdge")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Edge {
    @Id
    private int id;

    private boolean isEmpty;
    private int whichEdge;

    @OneToOne
    @JoinColumn(name="road_id", referencedColumnName = "id")
    private Road road;
}