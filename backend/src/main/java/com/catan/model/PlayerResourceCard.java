package com.catan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PlayerResourceCards")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlayerResourceCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    private Resource resource;

    public PlayerResourceCard(Player player, Resource resource){
        this.player = player;
        this.resource = resource;
    }
}
