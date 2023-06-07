package com.catan.dto;

import com.catan.exceptions.BadResourceNumberException;
import com.catan.model.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TradeWithBankDTOTest {
    //int resourceFromPlayer = 1, resourceFromBank = 0;
    //TradeWithBankDTO tradeTest = new TradeWithBankDTO(resourceFromPlayer, resourceFromBank);

    int number = 0;
    @Test
    void resourceWithOrdinal() {
        int resourceFromPlayer = 1, resourceFromBank = 0;
        Resource r = Resource.WOOD;
        int res = r.ordinal();
        Assertions.assertEquals(resourceFromBank, res);
    }

    @Test
    public void testConvertPlayerResourceFromInt() {
        TradeWithBankDTO tradeDTO = new TradeWithBankDTO(2, 4); // Assuming resourceFromPlayer = 2
        Resource playerResource = tradeDTO.convertPlayerResourceFromInt();
        Assertions.assertEquals(Resource.WOOL, playerResource);
    }

    @Test
    public void testConvertBankResourceFromInt() {
        TradeWithBankDTO tradeDTO = new TradeWithBankDTO(3, 1); // Assuming resourceFromBank = 1
        Resource bankResource = tradeDTO.convertBankResourceFromInt();
        Assertions.assertEquals(Resource.BRICK, bankResource);
    }

    @Test
    public void testInvalidPlayerResourceNumber() {
        int invalidPlayerResourceNumber = 10; // Assuming an invalid resource number
        TradeWithBankDTO tradeDTO = new TradeWithBankDTO(invalidPlayerResourceNumber, 4);
        Assertions.assertThrows(BadResourceNumberException.class, tradeDTO::convertPlayerResourceFromInt);
    }

    @Test
    public void testInvalidBankResourceNumber() {
        int invalidBankResourceNumber = 10; // Assuming an invalid resource number
        TradeWithBankDTO tradeDTO = new TradeWithBankDTO(2, invalidBankResourceNumber);
        Assertions.assertThrows(BadResourceNumberException.class, tradeDTO::convertBankResourceFromInt);
    }


}