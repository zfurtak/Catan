package com.catan.model.board;

import com.catan.model.Color;
import com.catan.model.Player;
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

    private int roadLength;
}