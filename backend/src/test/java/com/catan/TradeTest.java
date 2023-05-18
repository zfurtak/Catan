package com.catan;

import com.catan.exceptions.BadResourceNumberException;
import com.catan.model.Resource;
import org.junit.jupiter.api.Test;
import com.catan.dto.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TradeTest {
    private final int resourceFromPlayer = 2;
    private final int resourceFromBank = 1;
    private TradeWithBankDTO trade = new TradeWithBankDTO(resourceFromPlayer, resourceFromBank);

    @Test
    void ordinalPlayerTest() {
        for(int i = 0; i < Resource.values().length+1; i++) {
            trade = new TradeWithBankDTO(i, i);
            assertEquals(i, trade.convertPlayerResourceFromInt().ordinal());
            if(i >= Resource.values().length) {
                assertThrows(BadResourceNumberException.class, () -> trade.convertPlayerResourceFromInt().ordinal());
            }
        }
    }

    @Test
    void ordinalBankTest() {
        for(int i = 0; i < Resource.values().length+1; i++) {
            trade = new TradeWithBankDTO(i, i);
            assertEquals(i, trade.convertBankResourceFromInt().ordinal());
            if(i >= Resource.values().length) {
                assertThrows(BadResourceNumberException.class, () -> trade.convertBankResourceFromInt().ordinal());
            }
        }
    }
}
