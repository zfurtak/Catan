package com.catan.model.game;

import com.catan.model.player.Player;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Game {

    //TODO
    // change gameId to Singleton

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private List<Player> players;
    private Player currentPlayer;
    private Phase currentPhase;
    private Random random = new Random();

    public Game() {
        this.players = new ArrayList<>();
        currentPlayer = players.get(random.nextInt(4));
    }


}
