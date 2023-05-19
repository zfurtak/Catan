package com.catan.model.board;

import com.catan.model.Color;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Edge")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Edge {
    @Id
    private int id;

    @OneToOne
    @JoinColumn(name="road_id", referencedColumnName = "id")
    private Road road;


    public Color getColorOfEdge(){
        return this.getRoad().getPlayer().getColor();
    }
}
