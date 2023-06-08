package com.catan.model;

import com.catan.model.board.Field;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * Entity class for game objects, manages the connections in the database
 * @author Zuzanna Furtak
 */

@Entity
@Table(name = "Game")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int numberOfPlayers;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private List<Player> players;

    private int knightCardsTaken;
    private int pointCardsTaken;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name="field_id", referencedColumnName = "id")
    private List<Field> fields;

    private Phase currentPhase;

    public Game(List<Player> players, List<Field> fields){
        this.players = players;
        this.fields = fields;
        this.numberOfPlayers = 1;
        this.knightCardsTaken = 0;
        this.pointCardsTaken = 0;
        this.currentPhase = Phase.BUILDING;
    }
}
