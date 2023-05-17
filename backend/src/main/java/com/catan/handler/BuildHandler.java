package com.catan.handler;

import com.catan.exceptions.BuildingUnavailableException;
import com.catan.model.Game;
import com.catan.model.Player;
import com.catan.model.Resource;
import com.catan.model.board.*;
import com.catan.service.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BuildHandler {

    private final GameService gameService;
    private final FieldService fieldService;
    private final PlayerService playerService;
    private final EdgeService edgeService;
    private final VertexService vertexService;
    private final PlayerResourceCardService playerResourceCardService;


    public BuildHandler(GameService gameService,
                        FieldService fieldService,
                        PlayerService playerService,
                        EdgeService edgeService,
                        VertexService vertexService,
                        PlayerResourceCardService playerResourceCardService){
        this.gameService = gameService;
        this.fieldService = fieldService;
        this.playerService = playerService;
        this.edgeService = edgeService;
        this.vertexService = vertexService;
        this.playerResourceCardService = playerResourceCardService;
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
        if(!doesPlayerHaveEnoughResources(null, playerId)) {
            throw new BuildingUnavailableException("Not enough resources");
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
                                playerResourceCardService.deleteByPlayerIdAndResource(playerId, Resource.BRICK);
                                playerResourceCardService.deleteByPlayerIdAndResource(playerId, Resource.WOOD);
                                return gameService.getGame();
                            }
                        }
                    }else if(v.getBuilding().getPlayer().getColor() == playerDB.getColor()){
                        edgeService.updateEdge(edgeDB);
                        playerResourceCardService.deleteByPlayerIdAndResource(playerId, Resource.BRICK);
                        playerResourceCardService.deleteByPlayerIdAndResource(playerId, Resource.WOOD);
                        return gameService.getGame();
                    }
                }
            }
        }
        throw new BuildingUnavailableException("You cannot build road here");
    }

    // TODO: check if we have enough resources
    public Game buildVillage(int playerId, Vertex vertex) {
        Vertex vertexDB = vertexService.getVertex(vertex);
        if(vertexDB.getBuilding() != null){
            throw new BuildingUnavailableException("There is already a building there");
        }
        if(!doesPlayerHaveEnoughResources(BuildingType.VILLAGE, playerId)) {
            throw new BuildingUnavailableException("Not enough resources");
        }
        Player playerDB = playerService.getPlayerById(playerId);
        List<Edge> edges = vertexDB.getEdges();
        boolean canBuild = false;
        for(Edge e : edges) {
            if(e.getRoad() != null && e.getRoad().getPlayer().getColor() == playerDB.getColor()) {
                canBuild = true;
                break;
            }
        }
        if(!canBuild) {
            throw new BuildingUnavailableException("You cannot build in that vertex");
        }
        List<Field> fields = fieldService.getFieldsByVertex(vertexDB);
        for(Field f : fields) {
            List<Vertex> vertices = f.getVertices();
            for(Vertex v : vertices) {
                if(v.getId() != vertexDB.getId()) {
                    List<Edge> edgesConnected = v.getEdges();
                    for(Edge e : edgesConnected) {
                        if(edges.contains(e) && v.getBuilding() != null) {
                            throw new BuildingUnavailableException("You cannot build in that vertex");
                        }
                    }
                }
            }
        }
        Building building = new Building();
        building.setPlayer(playerDB);
        building.setType(BuildingType.VILLAGE);
        vertexDB.setBuilding(building);
        playerResourceCardService.deleteByPlayerIdAndResource(playerId, Resource.BRICK);
        playerResourceCardService.deleteByPlayerIdAndResource(playerId, Resource.WOOD);
        playerResourceCardService.deleteByPlayerIdAndResource(playerId, Resource.WOOL);
        playerResourceCardService.deleteByPlayerIdAndResource(playerId, Resource.WHEAT);
        return gameService.getGame();
    }

    public Game buildCity(int playerId, Vertex vertex) {
        Vertex vertexDB = vertexService.getVertex(vertex);
        if(vertexDB.getBuilding() != null && vertexDB.getBuilding().getPlayer().getId() == playerId){
            if(!doesPlayerHaveEnoughResources(BuildingType.CITY, playerId)) {
                throw new BuildingUnavailableException("Not enough resources");
            }
            vertexDB.getBuilding().setType(BuildingType.CITY);
            playerResourceCardService.deleteByPlayerIdAndResource(playerId, Resource.WHEAT);
            playerResourceCardService.deleteByPlayerIdAndResource(playerId, Resource.WHEAT);
            playerResourceCardService.deleteByPlayerIdAndResource(playerId, Resource.STONE);
            playerResourceCardService.deleteByPlayerIdAndResource(playerId, Resource.STONE);
            playerResourceCardService.deleteByPlayerIdAndResource(playerId, Resource.STONE);
            return gameService.getGame();
        }
        throw new BuildingUnavailableException("You cannot build the city here");
    }

    private boolean doesPlayerHaveEnoughResources(BuildingType buildingType, int playerId) {
        Map<Resource, Integer> resources = playerService.getPlayerResources(playerId);
        if(buildingType == null) {
            if(resources.getOrDefault(Resource.BRICK, 0) >= 1 &&
                    resources.getOrDefault(Resource.WOOD, 0) >= 1) {
                return true;
            }
        }
        else if(buildingType == BuildingType.VILLAGE) {
            if(resources.getOrDefault(Resource.BRICK, 0) >= 1 &&
                    resources.getOrDefault(Resource.WOOD, 0) >= 1 &&
                    resources.getOrDefault(Resource.WOOL, 0) >= 1 &&
                    resources.getOrDefault(Resource.WHEAT, 0) >= 1) {
                return true;
            }
        }
        else {
            if(resources.getOrDefault(Resource.WHEAT, 0) >= 2 &&
                    resources.getOrDefault(Resource.STONE, 0) >= 3) {
                return true;
            }
        }
        return false;
    }
}
