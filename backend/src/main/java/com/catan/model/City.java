package com.catan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "City")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="player_id", referencedColumnName = "id")
    private Player player;

    @ManyToOne
    @JoinColumn(name="vertex_id", referencedColumnName = "id")
    private FieldVertex fieldVertex;
}