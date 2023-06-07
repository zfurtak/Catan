package com.catan;

import com.catan.dto.TradeWithBankDTO;
import com.catan.model.*;
import com.catan.repository.GameRepository;
import com.catan.repository.PlayerRepository;
import com.catan.repository.PlayerResourceCardRepository;
import com.catan.repository.UserRepository;
import com.catan.service.GameService;
import com.catan.service.PlayerResourceCardService;
import com.catan.service.PlayerService;
import com.catan.service.UserService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

@SpringBootTest
public class IntegratedTradeTest {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameService gameService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerResourceCardRepository playerResourceCardRepository;
    @Autowired
    private PlayerResourceCardService playerResourceCardService;

    private User user1;
    private Player player1;
    private User user2;
    private Player player2;
    private Game game;
    private TradeWithBankDTO tradingBank;

    @BeforeEach
    void init() {
        gameRepository.deleteAll();
        playerRepository.deleteAll();
        playerResourceCardRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void getResourcesTest() {
        user1 = userService.registerUser("user1", "p1");
        game = gameService.joinGame(user1.getId()); 
        player1 = game.getPlayers().get(0);

        playerResourceCardService.addCard(player1, Resource.WOOD);
        playerResourceCardService.addCard(player1, Resource.WOOD);
        playerResourceCardService.addCard(player1, Resource.WOOD);
        playerResourceCardService.addCard(player1, Resource.WOOD);

        List<Integer> resources = gameService.getResourcesToTradeWithBank(player1.getId());

        assertEquals(Resource.WOOD.ordinal(), resources.get(0));
    }

    @Test
    void bankTradeTest() {
        tradingBank = new TradeWithBankDTO(Resource.WOOD.ordinal(), Resource.WOOL.ordinal());
        //game = gameService.tradeWithBank(0, tradingBank);
        assertEquals(Resource.WOOL.ordinal(), playerResourceCardService.findAllCardsByPlayerId(0).get(0));
    }
}
