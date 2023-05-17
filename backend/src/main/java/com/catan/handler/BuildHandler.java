package com.catan.handler;

import com.catan.exceptions.BuildingUnavailableException;
import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.model.board.Edge;
import com.catan.model.board.Field;
import com.catan.model.board.Road;
import com.catan.model.board.Vertex;
import com.catan.service.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuildHandler {

    private final GameService gameService;
    private final FieldService fieldService;
    private final PlayerService playerService;
    private final EdgeService edgeService;
    private final VertexService vertexService;


    public BuildHandler(GameService gameService,
                        FieldService fieldService,
                        PlayerService playerService,
                        EdgeService edgeService,
                        VertexService vertexService){
        this.gameService = gameService;
        this.fieldService = fieldService;
        this.playerService = playerService;
        this.edgeService = edgeService;
        this.vertexService = vertexService;
    }

    // we cannot build a road if it doesn't have direct connection with road or building in the same color
    // or if the edge is already taken
    // TODO: needs to be tested
    // TODO: checking the resources
    public Game buildRoad(int playerId, Edge edge){
        Edge edgeDB = edgeService.getEdge(edge.getId());
        if(edgeDB.getRoad() != null){
            throw new BuildingUnavailableException("There is already a road built");
        }
        Player playerDB = playerService.getPlayerById(playerId);
        Road road = new Road();
        road.setPlayer(playerDB);
        edgeDB.setRoad(road);

        List<Field> fields = fieldService.getFieldsByEdge(edgeDB);

        for(Field field: fields){
            for(Vertex v : field.getVertices()){
                if(v.getEdges().contains(edgeDB)){
                    if(v.getBuilding() == null) {
                        for(Edge e : v.getEdges()) {
                            if(e.getId() != edgeDB.getId() && e.getRoad() != null
                                    && e.getColorOfEdge() == playerDB.getColor()){
                                edgeService.updateEdge(edgeDB);
                                return gameService.getGame();
                            }
                        }
                    }else if(v.getBuilding().getPlayer().getColor() == playerDB.getColor()){
                        edgeService.updateEdge(edgeDB);
                        return gameService.getGame();
                    }
                }
            }
        }
        throw new BuildingUnavailableException("You cannot build road here");
    }

    // TODO: check if we have enough resources
    public Game buildVillage(int playerId, Vertex vertex){
        Vertex vertexDB = vertexService.getVertex(vertex);
        if(vertexDB.getBuilding() != null){
            throw new BuildingUnavailableException("There is already a building there");
        }
        return gameService.getGame();
    }

}
