package com.catan.model.player;
import com.catan.model.card.ResourcesCard;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.annotation.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Player")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private int points;

    @Transient
    private int victoryPoints;

//    Jest problem ze lista nie moze byc polem w bazie danych
//    @Transient
//    private List<ResourcesCard> resourcesCards = new ArrayList<>();

}
