package com.catan;

import com.catan.exceptions.BadResourceNumberException;
import com.catan.model.Player;
import com.catan.model.Resource;
import com.catan.service.GameService;
import com.catan.service.PlayerResourceCardService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.catan.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TradeTest {
    private final int resourceFromPlayer = 2;
    private final int resourceFromBank = 1;
    private TradeWithBankDTO trade = new TradeWithBankDTO(resourceFromPlayer, resourceFromBank);
    private static Player p;

    @BeforeAll
    static void init() {
        p = new Player();
        p.setId(0);
    }

    @Test
    void ordinalPlayerTest() {
        for(int i = 0; i < Resource.values().length+1; i++) {
            trade = new TradeWithBankDTO(i, i);
            if(i >= Resource.values().length) {
                Exception e = assertThrows(BadResourceNumberException.class, () -> trade.convertPlayerResourceFromInt());
                assertEquals("No resource with ordinal 6", e.getMessage());
            }
            else {
                assertEquals(i, trade.convertPlayerResourceFromInt().ordinal());
            }
        }
    }

    @Test
    void ordinalBankTest() {
        for(int i = 0; i < Resource.values().length+1; i++) {
            trade = new TradeWithBankDTO(i, i);
            if(i >= Resource.values().length) {
                assertThrows(BadResourceNumberException.class, () -> trade.convertBankResourceFromInt().ordinal());
            }
            else {
                assertEquals(i, trade.convertBankResourceFromInt().ordinal());
            }
        }
    }
}
