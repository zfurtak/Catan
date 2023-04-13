package com.catan.model.service;

import com.catan.model.player.Player;

import java.util.Optional;

public interface PlayerService {

    Optional<Player> getSpecificPlayer();

    void addPlayer(Player player);

    void updatePoints(Integer points);
}
