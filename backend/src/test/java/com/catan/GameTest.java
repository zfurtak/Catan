package com.catan;

import com.catan.exceptions.TooManyPlayersException;
import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.model.User;
import com.catan.repository.PlayerRepository;
import com.catan.repository.UserRepository;
import com.catan.service.GameService;
import com.catan.repository.GameRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class GameTest {
    @Autowired
    private GameRepository repository;
    @Autowired
    private GameService service;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlayerRepository playerRepository;

    private Player[] players;
    private Game game;
    List<Player> playerList = new ArrayList<>();
    private User users[];

    @BeforeEach
    void init() {
        players = new Player[4];
        users = new User[4];
        for(int i = 0; i < players.length; i++) {
            players[i] = new Player();
            users[i] = new User("user"+i, "p"+1);
            players[i].setId(i);
            players[i].setUser(users[i]);
        }
    }

    @AfterEach
    void delete() {
        repository.deleteAll();
        playerRepository.deleteAll();
        userRepository.deleteAll();
        playerList.clear();
    }

    @Test
    void createTest() {
        playerList.add(players[0]);
        game = service.createGame(players[1]);
        assertEquals(playerList, game.getPlayers());
        assertEquals(1, game.getNumberOfPlayers());
    }

    @Test
    void joinGameTest() {
        game = service.createGame(players[0]);
        playerList.add(players[0]);
        playerList.add(players[1]);
        game = service.joinExistingGame(game, players[1]);
        assertEquals(playerList, game.getPlayers());
        assertEquals(2, game.getNumberOfPlayers());
    }

    @Test
    void fullGameExceptionTest() {
        Player p5 = new Player();
        game = service.createGame(players[0]);
        for(int i = 1; i < players.length; i++) {
            game = service.joinExistingGame(game, players[i]);
        }
        assertThrows(TooManyPlayersException.class, () -> service.joinExistingGame(game, p5));
    }




}