package com.catan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @OneToOne
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private Board board;

    private int numberOfPlayers;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private List<Player> players;

    private int knightCardsTaken;
    private int pointCardsTaken;

    @OneToOne
    @JoinColumn(name = "current_player_id", referencedColumnName = "id")
    private Player currentPlayer;

    private Phase currentPhase;
}
