package com.catan.service;

import com.catan.exceptions.IncorrectGamesNumberException;
import com.catan.exceptions.PlayerAlreadyExistsException;
import com.catan.exceptions.TooManyPlayersException;
import com.catan.exceptions.UserAlreadyExistsException;
import com.catan.exceptions.UserNotFoundException;
import com.catan.model.Color;
import com.catan.model.Player;
import com.catan.model.PlayerResourceCard;
import com.catan.model.Resource;
import com.catan.model.User;
import com.catan.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service for the Player class.
 */
@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerResourceCardService playerResourceCardService;

    /**
     * Initialize the service.
     * @param playerRepository the Player repository associated to this service
     * @param playerResourceCardService the player resource card associated to this player
     */
    @Autowired
    public PlayerService(PlayerRepository playerRepository,
                         PlayerResourceCardService playerResourceCardService) {
        this.playerRepository = playerRepository;
        this.playerResourceCardService = playerResourceCardService;
    }

    /**
     * Returns the player with the specified id.
     * @exception UserNotFoundException if the player with the specified id is not saved in the repository
     * @param id id of the player to be returned
     * @return player with the specified id
     */
    public Player getPlayerById(int id) {
        Optional<Player> playerDB = playerRepository.findById(id);
        if (playerDB.isEmpty()) {
            throw new UserNotFoundException("Player not found");
        } else {
            return playerDB.get();
        }
    }

    /**
     * Returns the new player created with the specified user and the specified color.
     * @exception PlayerAlreadyExistsException if a player with the specified user is already saved in the 
     * @param user user of the player to be created
     * @param color color of the player to be created
     * @return new player with the specified user and password
     */
    public Player createPlayer(User user, Color color) {
        Optional<Player> playerDB = playerRepository.findByUserId(user.getId());
        if (playerDB.isPresent()) {
            //TODO add proper exception
            throw new PlayerAlreadyExistsException("Player is already created");
        } else {
            Player p = new Player();
            p.setColor(color);
            p.setUser(user);
            p.setNumberOfCities(0);
            p.setNumberOfVillages(0);
            p.setNumberOfRoads(0);
            p.setVictoryPoints(0);
            return playerRepository.save(p);
        }
    }

    /**
     * Adds the specified player to the repository. Returns the player that has been added.
     * @param player player to be added
     * @return the added player
     */
    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    /**
     * Updates the specified player's victory points with the new number specified in the points parameter. It returns the player with the updated points.
     * @param player player whose victory points will be updated
     * @param points number of victory points to be set
     * @return player with the updated victory points
     */
    public Player updateVictoryPoints(Player player, int points) {
        player.setVictoryPoints(points);
        player = this.playerRepository.save(player);
        return player;
    }

    /**
     * Returns a map with the resources and quantity of the player with the specified id.
     * @param playerId id of the player whose resources are got
     * @return map of the resources and quantity
     */
    public Map<Resource, Integer> getPlayerResources(int playerId) {
        List<PlayerResourceCard> resourceCards = playerResourceCardService.findAllCardsByPlayerId(playerId);
        Map<Resource, Integer> playerResources = new HashMap<>();
        for (PlayerResourceCard resourceCard : resourceCards) {
            Resource resource = resourceCard.getResource();
            if (playerResources.containsKey(resource)) {
                playerResources.put(resource, playerResources.get(resource) + 1);

            } else {
                playerResources.put(resource, 1);
            }
        }
        return playerResources;
    }


    /**
     * Returns a list with the resources of the player with the specified id that are fit to be traded with the bank. For a resource to be considered fit, the player needs to have at least four units of it.
     * @param playerId id of the player whose resources are got
     * @return list with the numbers corresponding to the resource types that can be traded with the bank
     */
    public List<Integer> getResourcesToTradeWithBank(int playerId){
        return this.getPlayerResources(playerId).entrySet()
                .stream()
                    .filter(x -> x.getValue() >= 4)
                    .map(x -> x.getKey().ordinal())
                        .toList();
    }

    /**
     * Takes four units of the specified resource from the player with the specified id. Then, it adds to the player's resources one unit of the specified resource from the bank.
     * @exception UserNotFoundException if the player with the specified id is not saved in the repository
     * @exception ResourceCardNotFoundException if the player with the specified id does not have cards saved in the repository
     * @param playerId id of the player who is trading with the bank
     * @param resourceFromPlayer resource type the player gives the bank
     * @param resourceFromBank resource type the bank gives the player
     */
    public void updateCardsAfterTradingWithBank(int playerId, Resource resourceFromPlayer, Resource resourceFromBank){
        for(int j = 0; j < 4; j++){
            playerResourceCardService.deleteResourceFromPlayer(playerId, resourceFromPlayer);
        }
        Player playerDB = this.getPlayerById(playerId);
        playerResourceCardService.addCard(playerDB, resourceFromBank);
    }

    /**
     * Takes the specified number of offered resources of the offering player and adds them to the resources of the accepting player. Then it takes the specified number of accepted resources from the accepting user and adds them to the resources of the offering user.
     * @param offeringPlayerId id of the player who offers to trade
     * @param acceptingPlayerId id of the player who is being offered a trade
     * @param resourceOffered resource type the offering player gives
     * @param resourceToGet resource type the accepting player gives
     * @param numResourcesOffered number of offered resources the offering player gives
     * @param numResourcesToGet number of accepted resources the accepting player gives
     */
    public void updateCardsAfterTradingWithPlayer(int offeringPlayerId, int acceptingPlayerId, Resource resourceOffered,
                                                  Resource resourceToGet, int numResourcesOffered, int numResourcesToGet){
        Player offeringPlayerDB = getPlayerById(offeringPlayerId);
        Player acceptingPlayerDB = getPlayerById(acceptingPlayerId);
        for (int i=0; i<numResourcesOffered; i++){
            playerResourceCardService.deleteResourceFromPlayer(offeringPlayerId, resourceOffered);
            playerResourceCardService.addCard(acceptingPlayerDB, resourceOffered);
        }
        for (int i=0; i<numResourcesToGet; i++){
            playerResourceCardService.deleteResourceFromPlayer(acceptingPlayerId, resourceToGet);
            playerResourceCardService.addCard(offeringPlayerDB, resourceToGet);
        }
    }

    /**
     * Updates the victory points of the player with the specified id, using the new number specified in the points parameter. It returns the player with the updated points.
     * @param id id of the player whose victory points will be updated
     * @param points number of victory points to be set
     * @return player with the updated victory points
     */
    public Player updateVictoryPoints(int id, int points) {
        return updateVictoryPoints(this.getPlayerById(id), points);
    }

    /**
     * Deletes all the players in the player's repository
     */
    public void deletePlayers() {
        playerRepository.deleteAll();
    }


    /**
     * Returns the color corresponding to the parameter provided. This method is called by GameService to provide the color for the new players.
     * @exception TooManyPlayersException if the number is not in the range of numbers from 0 to 3 (number of colors in Color class)
     * @param size number of color to get
     * @return the color associated to the size parameter
     */
    public Color getColor(int size){
        return switch (size) {
            case 0 -> Color.RED;
            case 1 -> Color.BLUE;
            case 2 -> Color.YELLOW;
            case 3 -> Color.WHITE;
            default -> throw new TooManyPlayersException("Problem with the number of players when choosing a color");
        };
    }
}
