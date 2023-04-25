package com.catan.model;
import jakarta.persistence.*;
import org.springframework.data.annotation.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Color color;
    private int victoryPoints;
    private int numberOfRoads;
    private int numberOfVillages;
    private int numberOfCities;
    // TODO: czy mozemy przechowywac w playerze liste kart
}
