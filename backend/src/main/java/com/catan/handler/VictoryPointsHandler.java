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

@Component
public class VictoryPointsHandler {
    private final PlayerService playerService;
    private final EdgeService edgeService;
    private final FieldService fieldService;
    private final GameService gameService;

    public VictoryPointsHandler(PlayerService playerService, EdgeService edgeService, GameService gameService,
                                FieldService fieldService) {
        this.playerService = playerService;
        this.edgeService = edgeService;
        this.gameService = gameService;
        this.fieldService = fieldService;
    }

    public Player updateVictoryPoints(int id) {
        return playerService.updateVictoryPoints(playerService.getPlayerById(id), calculateVictoryPoints(id));
    }

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

    private List<Edge> initialEdges(int id) {
        Player playerDB = playerService.getPlayerById(id);
        List<Edge> playerEdges = edgeService.findAllByColorOfEdge(playerDB.getColor());
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
