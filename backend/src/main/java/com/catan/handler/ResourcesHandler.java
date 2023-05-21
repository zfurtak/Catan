package com.catan.handler;

import com.catan.model.Player;
import com.catan.model.Resource;
import com.catan.model.board.Building;
import com.catan.model.board.BuildingType;
import com.catan.model.board.Field;
import com.catan.model.board.Vertex;
import com.catan.service.FieldService;
import com.catan.service.PlayerResourceCardService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Handler for the resources distribution
 */
@Component
public class ResourcesHandler {

    private final FieldService fieldService;
    private final PlayerResourceCardService playerResourceCardService;

    /**
     * Initialize the handler.
     * @param fieldService the Field service associated to this handler
     * @param playerResourceCardService the PlayerResourceCard service associated to this handler
     */
    public ResourcesHandler(FieldService fieldService,
                            PlayerResourceCardService playerResourceCardService) {
        this.fieldService = fieldService;
        this.playerResourceCardService = playerResourceCardService;
    }

    /**
     * Distributes resources between all the players who have villages or cities on the vertices of the fields that have the specified dice number.
     * @param diceNumber dice number of the fields that provide resources
     */
    public void distributeResources(int diceNumber) {
        List<Field> chosenFields = this.fieldService.findAllByDiceNumber(diceNumber);
        for (Field field : chosenFields) {
            if (!field.isBlocked()) {
                List<Vertex> vertices = field.getVertices();
                for (Vertex v : vertices) {
                    if (v.getBuilding() != null) {
                        Building building = v.getBuilding();
                        getResourceForPlayer(building.getPlayer(), field.getResource(), building.getType());
                    }
                }
            }
        }
    }

    /**
     * Adds the specified resource to the specified player. If the building is a village they get one unit. If it is a city, they get two.
     * @param player player who gets the resources
     * @param resource resouce type to be added
     * @param buildingType type of building the player has 
     */
    public void getResourceForPlayer(Player player, Resource resource, BuildingType buildingType) {
        if (buildingType == BuildingType.CITY) {
            playerResourceCardService.addCard(player, resource);
        }
        playerResourceCardService.addCard(player, resource);
    }
}
