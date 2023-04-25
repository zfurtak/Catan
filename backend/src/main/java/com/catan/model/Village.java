package com.catan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Village")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Village {
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
