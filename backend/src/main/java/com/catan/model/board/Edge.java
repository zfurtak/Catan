package com.catan.model.board;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Edge")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Edge {
    @Id
    private int id;

    private boolean isEmpty;

    @OneToOne
    @JoinColumn(name="road_id", referencedColumnName = "id")
    private Road road;
}
