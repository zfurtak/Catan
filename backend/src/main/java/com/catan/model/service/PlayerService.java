package com.catan.model.service;

import com.catan.model.player.Player;

import java.util.Optional;

public interface PlayerService {

    Player getPlayerByName(String username);

    Player getPlayerById(int id);

    Player logPlayerIn(String username, String password);

    Player addPlayer(Player player);

    Player updatePoints(Player player, int points);
    Player updatePoints(int id, int points);
}
