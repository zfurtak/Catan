package com.catan.service;

import com.catan.model.Player;

public interface PlayerService {

    Player getPlayerByName(String username);

    Player getPlayerById(int id);

    Player logPlayerIn(String username, String password);

    Player addPlayer(Player player);

    Player updatePoints(Player player, int points);
    Player updatePoints(int id, int points);
}
