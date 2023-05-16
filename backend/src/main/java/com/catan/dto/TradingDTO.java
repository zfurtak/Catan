package com.catan.dto;

import com.catan.exceptions.BadResourceNumberException;
import com.catan.model.Resource;

// TODO: reformat code somehow?
public class TradingDTO {
    private final int resourceFromPlayer;
    private final int resourceFromBank;
    private final int resourceToOffer;
    private final int numberOfResourcesOffered;
    private final int resourceToGet;
    private final int numberOfResourcesToGet;

    public TradingDTO(int resourceFromPlayer, int resourceFromBank) {
        this.resourceFromBank = resourceFromBank;
        this.resourceFromPlayer = resourceFromPlayer;
        this.resourceToOffer = 0;
        this.numberOfResourcesOffered = 0;
        this.resourceToGet = 0;
        this.numberOfResourcesToGet = 0;
    }

    public TradingDTO(int resourceToOffer, int numberOfResourcesOffered,
                      int resourceToGet, int numberOfResourcesToGet) {
        this.resourceFromBank = 0;
        this.resourceFromPlayer = 0;
        this.resourceToOffer = resourceToOffer;
        this.numberOfResourcesOffered = numberOfResourcesOffered;
        this.resourceToGet = resourceToGet;
        this.numberOfResourcesToGet = numberOfResourcesToGet;
    }

    public Resource convertPlayerResourceFromInt() {
        return resourceWithOrdinal(resourceFromPlayer);
    }

    public Resource convertBankResourceFromInt() {
        return resourceWithOrdinal(resourceFromBank);
    }

    public Resource convertResourceToOffer() { return resourceWithOrdinal(resourceToOffer); }
    public Resource convertResourceToGet() { return resourceWithOrdinal(resourceToGet); }

    public int getNumberOfResourcesOffered() { return numberOfResourcesOffered; }
    public int getNumberOfResourcesToGet() { return numberOfResourcesToGet; }

    private Resource resourceWithOrdinal(int ordinal) {
        for (Resource resource : Resource.values()) {
            if (resource.ordinal() == ordinal) {
                return resource;
            }
        }
        throw new BadResourceNumberException("No resource with ordinal " + ordinal);
    }
}
