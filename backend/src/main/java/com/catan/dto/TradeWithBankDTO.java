package com.catan.dto;

import com.catan.model.Resource;

public class TradeWithBankDTO {
    private final int resourceFromPlayer;
    private final int resourceFromBank;

    public TradeWithBankDTO(int resourceFromPlayer, int resourceFromBank) {
        this.resourceFromBank = resourceFromBank;
        this.resourceFromPlayer = resourceFromPlayer;
    }

    public Resource getResourceFromPlayer() {
        return resourceWithOrdinal(resourceFromPlayer);
    }

    public Resource getResourceFromBank() {
        return resourceWithOrdinal(resourceFromBank);
    }

    private Resource resourceWithOrdinal(int ordinal) {
        Resource res = null;
        for(Resource resource : Resource.values()) {
            if(resource.ordinal() == ordinal) {
                res = resource;
            }
        }
        return res;
    }
}
