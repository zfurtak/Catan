package com.catan.dto;

import com.catan.exceptions.BadResourceNumberException;
import com.catan.model.Resource;

/**
 * Class for managing general Trading.
 * @author rorro6787
 * @author Zuzanna Furtak
 * @author Agnieszka Lasek
 */
public class TradingDTO {
    private final int resourceFromPlayer;
    private final int resourceFromBank;
    private final int resourceToOffer;
    private final int numberOfResourcesOffered;
    private final int resourceToGet;
    private final int numberOfResourcesToGet;

    /**
     * Initialize the class for Trading with Bank.
     * @param resourceFromPlayer resource type the player gives
     * @param resourceFromBank resource type the bank gives
     */
    public TradingDTO(int resourceFromPlayer, int resourceFromBank) {
        this.resourceFromBank = resourceFromBank;
        this.resourceFromPlayer = resourceFromPlayer;
        this.resourceToOffer = 0;
        this.numberOfResourcesOffered = 0;
        this.resourceToGet = 0;
        this.numberOfResourcesToGet = 0;
    }

    /**
     * Initialize the class for Trading with a Player.
     * @param resourceToOffer resource type the player offers
     * @param numberOfResourcesOffered number of offered resources
     * @param resourceToGet resource type the player wants to get
     * @param numberOfResourcesToGet number of resources the player wants to get
     */
    public TradingDTO(int resourceToOffer, int numberOfResourcesOffered,
                      int resourceToGet, int numberOfResourcesToGet) {
        this.resourceFromBank = 0;
        this.resourceFromPlayer = 0;
        this.resourceToOffer = resourceToOffer;
        this.numberOfResourcesOffered = numberOfResourcesOffered;
        this.resourceToGet = resourceToGet;
        this.numberOfResourcesToGet = numberOfResourcesToGet;
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
     * Returns the Resource type associated to resourceToOffer.
     * @return Resource type associated to resourceToOffer
     */
    public Resource convertResourceToOffer() {
        return resourceWithOrdinal(resourceToOffer);
    }

    /**
     * Returns the Resource type associated to resourceToGet.
     * @return  Resource type associated to resourceToGet
     */
    public Resource convertResourceToGet() {
        return resourceWithOrdinal(resourceToGet);
    }

    /**
     * Retruns numberOfResourcesOffered.
     * @return numberOfResourcesOffered
     */
    public int getNumberOfResourcesOffered() {
        return numberOfResourcesOffered;
    }
    
    /**
     * Returns numberOfResourcesToGet.
     * @return numberOfResourcesToGet
     */
    public int getNumberOfResourcesToGet() {
        return numberOfResourcesToGet;
    }

    /**
     * Returns the resource associated to the specified number.
     * @exception BadResourceNumberException if the specified number is out of the range for the Resource class
     * @param ordinal number of the resource to convert
     * @return resource associated to the ordinal parameter
     */
    private Resource resourceWithOrdinal(int ordinal) {
        for (Resource resource : Resource.values()) {
            if (resource.ordinal() == ordinal) {
                return resource;
            }
        }
        throw new BadResourceNumberException("No resource with ordinal " + ordinal);
    }
}
