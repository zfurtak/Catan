package com.catan.model.game;

import com.catan.model.player.Player;
import org.springframework.stereotype.Service;

@Service
public interface GameService {

    Game createGame(int phaseTimeLimit, int requiredVictoryPoints);
    Game connectToGame(Player player, int gameId);

}
