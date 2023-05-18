package com.catan;

import com.catan.exceptions.TooManyPlayersException;
import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.repository.PlayerRepository;
import com.catan.repository.UserRepository;
import com.catan.service.GameService;
import com.catan.service.UserService;
import com.catan.repository.GameRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class GameTest {
    @Autowired
    private final GameRepository repository;
    @Autowired
    private final GameService service;
    @Autowired
    private final UserService userService;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PlayerRepository playerRepository;

    private Player p1, p2, p3, p4;
    private Game game;
    private List<Player> playerList;

    @AfterEach
    void delete() {
        repository.deleteAll();
        playerRepository.deleteAll();
        userRepository.deleteAll();
        playerList.removeAll();
    }

    @Test
    void createTest() {
        p1 = new Player();  //TODO fix constructor when fixed database
        playerList.add(p1);
        game = service.createGame(p1);
        assertEquals(playerList, game.getPlayers());
        assertEquals(1, game.getNumberOfPlayers());
    }

    @Test
    void joinGameTest() {
        p1 = new Player();
        p2 = new Player();
        game = service.createGame(p1);
        playerList.addAll({p1, p2});
        Game resultingGame = service.joinExistingGame(game, p2);
        assertEquals(playerList, game.getPlayers());
    }

    @Test
    void fullGameExceptionTest {
        p1 = new Player();
        p2 = new Player();
        p3 = new Player();
        p4 = new Player();
        Player p5 = new Player();
        game = service.createGame(p1);
        playerList.addAll({p1, p2, p3, p4});
        Game resultingGame = service.joinExistingGame(game, p2);
        resultingGame = service.joinExistingGame(game, p3);
        resultingGame = service.joinExistingGame(game, p4);
        assertThrows(TooManyPlayersException.class, () -> service.joinExistingGame(game, p5));
    }

    @Test
    void tradeTest() {

    }
}