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

@Component
public class ResourcesHandler {

    private final FieldService fieldService;
    private final PlayerResourceCardService playerResourceCardService;

    public ResourcesHandler(FieldService fieldService,
                            PlayerResourceCardService playerResourceCardService) {
        this.fieldService = fieldService;
        this.playerResourceCardService = playerResourceCardService;
    }

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

    public void getResourceForPlayer(Player player, Resource resource, BuildingType buildingType) {
        if (buildingType == BuildingType.CITY) {
            playerResourceCardService.addCard(player, resource);
        }
        playerResourceCardService.addCard(player, resource);
    }
}
