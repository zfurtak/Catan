package com.catan.service;

import com.catan.model.Player;
import com.catan.model.PlayerResourceCard;
import com.catan.model.Resource;
import com.catan.repository.PlayerResourceCardRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerResourceCardService {
    private final PlayerResourceCardRepository resourceCardRepository;

    public PlayerResourceCardService(PlayerResourceCardRepository playerResourceCardRepository){
        this.resourceCardRepository = playerResourceCardRepository;
    }

    public void addCard(Player player, Resource resource){
        PlayerResourceCard playerResourceCard = new PlayerResourceCard(player, resource);
        this.resourceCardRepository.save(playerResourceCard);
    }
}
