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
public class PlayerResourceCards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "resource_card_id", referencedColumnName = "id")
    private ResourceCard resourceCard;
}
