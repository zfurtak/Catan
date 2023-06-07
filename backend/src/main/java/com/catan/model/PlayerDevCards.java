package com.catan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "PlayerDevCards")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlayerDevCards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "development_card_id", referencedColumnName = "id")
    private DevelopmentCard developmentCard;

}
