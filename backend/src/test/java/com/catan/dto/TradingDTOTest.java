package com.catan.dto;

import com.catan.exceptions.BadResourceNumberException;
import com.catan.model.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TradingDTOTest {

    @Test
    public void testConvertPlayerResourceFromInt() {
        TradingDTO tradingDTO = new TradingDTO(2, 4); // Assuming resourceFromPlayer = 2
        Resource playerResource = tradingDTO.convertPlayerResourceFromInt();
        Assertions.assertEquals(Resource.WOOL, playerResource);
    }

    @Test
    public void testConvertBankResourceFromInt() {
        TradingDTO tradingDTO = new TradingDTO(3, 1); // Assuming resourceFromBank = 3
        Resource bankResource = tradingDTO.convertBankResourceFromInt();
        Assertions.assertEquals(Resource.BRICK, bankResource);
    }

    @Test
    public void testConvertResourceToOffer() {
        TradingDTO tradingDTO = new TradingDTO(1, 3, 4, 2); // Assuming resourceToOffer = 1
        Resource resourceToOffer = tradingDTO.convertResourceToOffer();
        Assertions.assertEquals(Resource.BRICK, resourceToOffer);
    }

    @Test
    public void testConvertResourceToGet() {
        TradingDTO tradingDTO = new TradingDTO(2, 3, 5, 2); // Assuming resourceToGet = 5
        Resource resourceToGet = tradingDTO.convertResourceToGet();
        Assertions.assertEquals(Resource.DESSERT, resourceToGet);
    }

    @Test
    public void testGetNumberOfResourcesOffered() {
        TradingDTO tradingDTO = new TradingDTO(1, 4, 3, 2);
        int numberOfResourcesOffered = tradingDTO.getNumberOfResourcesOffered();
        Assertions.assertEquals(4, numberOfResourcesOffered);
    }

    @Test
    public void testGetNumberOfResourcesToGet() {
        TradingDTO tradingDTO = new TradingDTO(2, 3, 5, 2);
        int numberOfResourcesToGet = tradingDTO.getNumberOfResourcesToGet();
        Assertions.assertEquals(2, numberOfResourcesToGet);
    }

    @Test
    public void testInvalidPlayerResourceNumber() {
        int invalidPlayerResourceNumber = 10; // Assuming an invalid resource number
        TradingDTO tradingDTO = new TradingDTO(invalidPlayerResourceNumber, 4);
        Assertions.assertThrows(BadResourceNumberException.class, tradingDTO::convertPlayerResourceFromInt);
    }

    @Test
    public void testInvalidBankResourceNumber() {
        int invalidBankResourceNumber = 10; // Assuming an invalid resource number
        TradingDTO tradingDTO = new TradingDTO(2, invalidBankResourceNumber);
        Assertions.assertThrows(BadResourceNumberException.class, tradingDTO::convertBankResourceFromInt);
    }

    @Test
    public void testInvalidResourceToOffer() {
        int invalidResourceToOffer = 10; // Assuming an invalid resource number
        TradingDTO tradingDTO = new TradingDTO(invalidResourceToOffer, 3, 4, 2);
        Assertions.assertThrows(BadResourceNumberException.class, tradingDTO::convertResourceToOffer);
    }

    @Test
    public void testInvalidResourceToGet() {
        int invalidResourceToGet = 10; // Assuming an invalid resource number
        TradingDTO tradingDTO = new TradingDTO(1, 3, invalidResourceToGet, 2);
        Assertions.assertThrows(BadResourceNumberException.class, tradingDTO::convertResourceToGet);
    }


}