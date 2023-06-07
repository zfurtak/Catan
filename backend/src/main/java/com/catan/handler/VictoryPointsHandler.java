package com.catan.handler;

import com.catan.model.Player;
import com.catan.model.board.Edge;
import com.catan.model.board.Field;
import com.catan.model.board.Vertex;
import com.catan.service.EdgeService;
import com.catan.service.FieldService;
import com.catan.service.GameService;
import com.catan.service.PlayerService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Handler for updating players' victory points
 */
@Component
public class VictoryPointsHandler {
    private final PlayerService playerService;
    private final EdgeService edgeService;
    private final FieldService fieldService;
    private final GameService gameService;

    /**
     * Initialize the handler.
     * @param playerService the Player service associated to this handler
     * @param edgeService the Edge service associated to this handler
     * @param gameService the Game service associated to this handler
     * @param fieldService the Field service associated to this handler
     */
    public VictoryPointsHandler(PlayerService playerService, EdgeService edgeService, GameService gameService,
                                FieldService fieldService) {
        this.playerService = playerService;
        this.edgeService = edgeService;
        this.gameService = gameService;
        this.fieldService = fieldService;
    }

    /**
     * Updates the victory points from the player with the specified id.
     * @param id id of the player whose victory points are updated
     * @return player with updated victory points
     */
    public Player updateVictoryPoints(int id) {
        return playerService.updateVictoryPoints(playerService.getPlayerById(id), calculateVictoryPoints(id));
    }

    /**
     * Calculates the victory points of the player with the specified id. This method is called by the method updateVictoryPoints.
     * @param id id of the player whose victory points are calculated
     * @return number of victory points the player has.
     */
    private int calculateVictoryPoints(int id) {
        Player playerDB = playerService.getPlayerById(id);
        List<Edge> initialEdges = initialEdges(id);
        Integer victoryPoints = initialEdges == null || initialEdges.isEmpty() ? 0 : 1;
        int maxValue = victoryPoints.intValue();
        for(Edge e : initialEdges) {
            calculateVictoryPoints(e, victoryPoints, maxValue);
        }
        return victoryPoints;
    }

    /**
     * 
     * @param e
     * @param victoryPoints
     * @param maxValue
     */
    private void calculateVictoryPoints(Edge e, Integer victoryPoints, int maxValue) {
        if(maxValue > victoryPoints) {
            victoryPoints = maxValue;
        }
        for(Field f : fieldService.getFieldsByEdge(e)) {
            List<Edge> visitedEdges = new ArrayList<>();
            for(Vertex v : f.getVertices()) {
                for(Edge e2 : v.getEdges()) {
                    if(!visitedEdges.contains(e2) && !e2.equals(e)) {
                        calculateVictoryPoints(e2, victoryPoints, maxValue+1);
                    }
                    visitedEdges.add(e2);
                }
            }
        }
    }

    /**
     * 
     * @param id
     * @return
     */
    private List<Edge> initialEdges(int id) {
        Player playerDB = playerService.getPlayerById(id);
//        List<Edge> playerEdges = edgeService.findAllByColorOfEdge(playerDB.getColor());
        List<Edge> playerEdges = new ArrayList<>();
        List<Edge> initialEdges = new ArrayList<>();
        for(Edge e : playerEdges) {
            List<Field> fieldsByEdge = fieldService.getFieldsByEdge(e);
            int counterOfClosedEdgesWithSameColor = 0;
            for(Field f : fieldsByEdge) {
                List<Edge> visitedEdges = new ArrayList<>();
                for(Vertex v : f.getVertices()) {
                    for(Edge e2 : v.getEdges()) {
                        if(!visitedEdges.contains(e2) && !e2.equals(e)) {
                            if(e2.getColorOfEdge() == e.getColorOfEdge()) {
                                ++counterOfClosedEdgesWithSameColor;
                            }
                        }
                        visitedEdges.add(e2);
                    }
                }
            }
            if(counterOfClosedEdgesWithSameColor < 2) {
                initialEdges.add(e);
            }
        }
        return initialEdges;
    }
}
