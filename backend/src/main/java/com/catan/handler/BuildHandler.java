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

/**
 * Handler for Building
 */
@Component
public class BuildHandler {

    private final GameService gameService;
    private final FieldService fieldService;
    private final PlayerService playerService;
    private final EdgeService edgeService;
    private final VertexService vertexService;
    private final PlayerResourceCardService playerResourceCardService;

/**
 * Initialize the handler.
 * @param gameService the Game service associated to this handler
 * @param fieldService the Field service associated to this handler
 * @param playerService the player service associated to this handler
 * @param edgeService the Edge service associated to this handler
 * @param vertexService the Vertex service associated to this handler
 * @param playerResourceCardService the PlayerResourceCard service associated to this handler
 */
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
    /**
     * Builds a road for the player with the specified id in the specified edge. Places the building and takes the resource cards necessary from the player. Returns the game where this action is happening.
     * @exception BuildingUnavailableException if the player does not have enough resources, the edge is occupied with another road or it is not directly connected to a road or building from the player.
     * @param playerId id of the player that builds
     * @param edge edge where the road is built
     * @return game where this action is activated
     */
    public Game buildRoad(int playerId, Edge edge){
        Edge edgeDB = edgeService.getEdge(edge.getId());
        if(edgeDB.getRoad() != null){
            throw new BuildingUnavailableException("There is already a road built");
        }
        if(hasEnoughResourcesToBuild(null, playerId)) {
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
                                playerResourceCardService.deleteResourceFromPlayer(playerId, Resource.BRICK);
                                playerResourceCardService.deleteResourceFromPlayer(playerId, Resource.WOOD);
                                return gameService.getGame();
                            }
                        }
                    }else if(v.getBuilding().getPlayer().getColor() == playerDB.getColor()){
                        edgeService.updateEdge(edgeDB);
                        playerResourceCardService.deleteResourceFromPlayer(playerId, Resource.BRICK);
                        playerResourceCardService.deleteResourceFromPlayer(playerId, Resource.WOOD);
                        return gameService.getGame();
                    }
                }
            }
        }
        throw new BuildingUnavailableException("You cannot build road here");
    }

    /**
     * Builds a village for the player with the specified id in the specified vertex. Places the building and takes the resource cards necessary from the player. Returns the game where this action is happening.
     * @exception BuildingUnavailableException if the player does not have enough resources or the vertex is occupied with another building
     * @param playerId id of the player that builds
     * @param vertex vertex where the village is built
     * @return game where this action is activated
     */
    public Game buildVillage(int playerId, Vertex vertex) {
        Vertex vertexDB = vertexService.getVertex(vertex);
        if(vertexDB.getBuilding() != null){
            throw new BuildingUnavailableException("There is already a building there");
        }
        if(hasEnoughResourcesToBuild(BuildingType.VILLAGE, playerId)) {
            throw new BuildingUnavailableException("Not enough resources");
        }
        Player playerDB = playerService.getPlayerById(playerId);
        List<Edge> edges = vertexDB.getEdges();
        boolean canBuild = false;
        for(Edge e : edges) {
            if(e.getRoad() != null && e.getColorOfEdge() == playerDB.getColor()) {
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
        playerResourceCardService.deleteResourceFromPlayer(playerId, Resource.BRICK);
        playerResourceCardService.deleteResourceFromPlayer(playerId, Resource.WOOD);
        playerResourceCardService.deleteResourceFromPlayer(playerId, Resource.WOOL);
        playerResourceCardService.deleteResourceFromPlayer(playerId, Resource.WHEAT);
        return gameService.getGame();
    }

    /**
     * Builds a city for the player with the specified id in the specified vertex. Places the building and takes the resource cards necessary from the player. It is necessary for the player to have already built a village on the vertex. Returns the game where this action is happening.
     * @exception BuildingUnavailableException if the player does not have enough resources or if there is not a village in the vertex belonging to the player
     * @param playerId id of the player that builds
     * @param vertex vertex where the city is built
     * @return game where this action is activated
     */
    public Game buildCity(int playerId, Vertex vertex) {
        Vertex vertexDB = vertexService.getVertex(vertex);
        if(vertexDB.getBuilding() != null && vertexDB.getBuilding().getPlayer().getId() == playerId){
            if(hasEnoughResourcesToBuild(BuildingType.CITY, playerId)) {
                throw new BuildingUnavailableException("Not enough resources");
            }
            vertexDB.getBuilding().setType(BuildingType.CITY);
            playerResourceCardService.deleteResourceFromPlayer(playerId, Resource.WHEAT);
            playerResourceCardService.deleteResourceFromPlayer(playerId, Resource.WHEAT);
            playerResourceCardService.deleteResourceFromPlayer(playerId, Resource.STONE);
            playerResourceCardService.deleteResourceFromPlayer(playerId, Resource.STONE);
            playerResourceCardService.deleteResourceFromPlayer(playerId, Resource.STONE);
            return gameService.getGame();
        }
        throw new BuildingUnavailableException("You cannot build the city here");
    }

    /**
     * Returns true if the specified player has enough resources to build a building of the specified type.
     * @param buildingType type of the building the player wants to build
     * @param playerId id of the player trying to build
     * @return boolean that is true if the player has enough resources, else false
     */
    private boolean hasEnoughResourcesToBuild(BuildingType buildingType, int playerId) {
        Map<Resource, Integer> resources = playerService.getPlayerResources(playerId);
        if(buildingType == null) {
            return resources.getOrDefault(Resource.BRICK, 0) < 1 ||
                    resources.getOrDefault(Resource.WOOD, 0) < 1;
        }
        else if(buildingType == BuildingType.VILLAGE) {
            return resources.getOrDefault(Resource.BRICK, 0) < 1 ||
                    resources.getOrDefault(Resource.WOOD, 0) < 1 ||
                    resources.getOrDefault(Resource.WOOL, 0) < 1 ||
                    resources.getOrDefault(Resource.WHEAT, 0) < 1;
        }
        else {
            return resources.getOrDefault(Resource.WHEAT, 0) < 2 ||
                    resources.getOrDefault(Resource.STONE, 0) < 3;
        }
    }
}
