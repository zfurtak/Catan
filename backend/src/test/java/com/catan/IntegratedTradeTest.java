package com.catan;

import com.catan.dto.TradeWithBankDTO;
import com.catan.model.*;
import com.catan.repository.GameRepository;
import com.catan.repository.PlayerRepository;
import com.catan.repository.UserRepository;
import com.catan.service.GameService;
import com.catan.service.PlayerResourceCardService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegratedTradeTest {
    @Autowired
    private GameRepository repository;
    @Autowired
    private GameService service;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayerResourceCardService playerResourceCardService;

    private Player[] players = new Player[4];
    private Game game;
    private TradeWithBankDTO tradingBank;
    private User[] users = new User[4];

    @BeforeEach
    void init() {
        for(int i = 0; i < players.length; i++) {
            users[i] = new User("user"+i, "p"+i);
            players[i] = new Player();
            players[i].setId(i);
            players[i].setUser(users[i]);
            playerResourceCardService.addCard(players[i], Resource.WOOD);
        }
    }

    @Test
    void getResourcesTest() {
        assertEquals(Resource.WOOD.ordinal(),
                service.getResourcesToTradeWithBank(0).get(0));
    }

    @Test
    void bankTradeTest() {
        tradingBank = new TradeWithBankDTO(Resource.WOOD.ordinal(), Resource.WOOL.ordinal());
        game = service.tradeWithBank(0, tradingBank);
        assertEquals(Resource.WOOL.ordinal(), playerResourceCardService.findAllCardsByPlayerId(0).get(0));
    }
}
