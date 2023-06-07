package com.catan.dto;

import com.catan.exceptions.BadResourceNumberException;
import com.catan.model.Resource;

/**
 * Class for managing Trading with the Bank.
 */
public class TradeWithBankDTO {
    private final int resourceFromPlayer;
    private final int resourceFromBank;

    /**
     * Initialize the class.
     * @param resourceFromPlayer resource type the player gives
     * @param resourceFromBank resource type the bank gives
     */
    public TradeWithBankDTO(int resourceFromPlayer, int resourceFromBank) {
        this.resourceFromBank = resourceFromBank;
        this.resourceFromPlayer = resourceFromPlayer;
    }

    /**
     * Returns the Resource type associated to resourceFromPlayer.
     * @return Resource type associated to resourceFromPlayer
     */
    public Resource convertPlayerResourceFromInt() {
        return resourceWithOrdinal(resourceFromPlayer);
    }

    /**
     * Returns the Resource type associated to resourceFromBank.
     * @return Resource type associated to resourceFromBank
     */
    public Resource convertBankResourceFromInt() {
        return resourceWithOrdinal(resourceFromBank);
    }

    /**
     * Returns the resource associated to the specified number.
     * @exception BadResourceNumberException if the specified number is out of the range for the Resource class
     * @param ordinal number of the resource to convert
     * @return resource associated to the ordinal parameter
     */
    private Resource resourceWithOrdinal(int ordinal) {
        for (Resource resource : Resource.values()) {
            if(resource.ordinal() == ordinal) {
                return resource;
            }
        }
        throw new BadResourceNumberException("No resource with ordinal " + ordinal);
    }
}
