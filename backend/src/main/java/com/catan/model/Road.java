package com.catan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Road")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Road {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="player_id", referencedColumnName = "id")
    private Player player;

    @ManyToOne
    @JoinColumn(name="edge_id", referencedColumnName = "id")
    private FieldEdge fieldEdge;

    private int roadLength;
}