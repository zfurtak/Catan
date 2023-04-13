package com.catan.model.game;

import com.catan.model.player.Player;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Game {

    //TODO
    // remove gameId

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private List<Player> players;
    private final int phaseTimeLimit;
    private final int requiredVictoryPoints;
    private Player currentPlayer;
    private Phase currentPhase;

    public Game(int phaseTimeLimit, int requiredVictoryPoints) {
        this.phaseTimeLimit = phaseTimeLimit;
        this.requiredVictoryPoints = requiredVictoryPoints;
    }
}
