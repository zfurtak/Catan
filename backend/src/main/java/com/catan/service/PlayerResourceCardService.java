package com.catan.service;

import com.catan.model.Player;
import com.catan.model.PlayerResourceCard;
import com.catan.model.Resource;
import com.catan.repository.PlayerResourceCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerResourceCardService {
    private final PlayerResourceCardRepository playerResourceCardRepository;

    public PlayerResourceCardService(PlayerResourceCardRepository playerResourceCardRepository) {
        this.playerResourceCardRepository = playerResourceCardRepository;
    }

    public void addCard(Player player, Resource resource) {
        PlayerResourceCard playerResourceCard = new PlayerResourceCard(player, resource);
        this.playerResourceCardRepository.save(playerResourceCard);
    }

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    public List<PlayerResourceCard> findAllCardsByPlayerId(int playerId){
        return playerResourceCardRepository.findAllByPlayerId(playerId);
    }

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    public List<PlayerResourceCard> getPlayersCardNumber(Player player) {
        return playerResourceCardRepository.findAllByPlayerId(player.getId());
    }

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    public void deleteByPlayerIdAndResource(int playerId, Resource resource){
        this.playerResourceCardRepository.deleteByPlayerIdAndResource(playerId, resource);
    }

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    public void deleteById(int id) {
        this.playerResourceCardRepository.deleteById(id);
    }
}
