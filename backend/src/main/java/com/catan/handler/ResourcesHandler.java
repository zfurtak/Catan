package com.catan.handler;

import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.model.Resource;
import com.catan.model.board.Building;
import com.catan.model.board.BuildingType;
import com.catan.model.board.Field;
import com.catan.model.board.Vertex;
import com.catan.service.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourcesHandler {

    private final GameService gameService;
    private final FieldService fieldService;
    private final VertexService vertexService;
    private final PlayerService playerService;
    private final PlayerResourceCardService playerResourceCardService;

    public ResourcesHandler(GameService gameService,
                            FieldService fieldService,
                            VertexService vertexService,
                            PlayerService playerService,
                            PlayerResourceCardService playerResourceCardService){
        this.gameService = gameService;
        this.fieldService = fieldService;
        this.vertexService = vertexService;
        this.playerService = playerService;
        this.playerResourceCardService = playerResourceCardService;
    }

    public Game distributeResources(int diceNumber){
        Game game = gameService.getGame();
        List<Field> chosenFields = this.fieldService.findAllByDiceNumber(diceNumber);
        for(Field field: chosenFields){
            if(!field.isBlocked()){
                List<Vertex> vertices = field.getVertices();
                for(Vertex v: vertices){
                    if(v.getBuilding() != null) {
                        Building building = v.getBuilding();
                        getResourceForPlayer(building.getPlayer(), field.getResource(), building.getType());
                    }
                }
            }
        }
        return game;
    }

    public void getResourceForPlayer(Player player, Resource resource, BuildingType buildingType){
        if(buildingType == BuildingType.CITY){
            playerResourceCardService.addCard(player, resource);
        }
        playerResourceCardService.addCard(player, resource);
    }
}
