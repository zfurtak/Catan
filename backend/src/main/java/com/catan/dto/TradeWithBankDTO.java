package com.catan.dto;

import com.catan.exceptions.BadResourceNumberException;
import com.catan.model.Resource;

public class TradeWithBankDTO {
    private final int resourceFromPlayer;
    private final int resourceFromBank;

    public TradeWithBankDTO(int resourceFromPlayer, int resourceFromBank) {
        this.resourceFromBank = resourceFromBank;
        this.resourceFromPlayer = resourceFromPlayer;
    }

    public Resource convertPlayerResourceFromInt() {
        return resourceWithOrdinal(resourceFromPlayer);
    }

    public Resource convertBankResourceFromInt() {
        return resourceWithOrdinal(resourceFromBank);
    }

    private Resource resourceWithOrdinal(int ordinal) {
        for (Resource resource : Resource.values()) {
            if(resource.ordinal() == ordinal) {
                return resource;
            }
        }
        throw new BadResourceNumberException("No resource with ordinal " + ordinal);
    }
}
