package com.catan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FieldEdge")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FieldEdge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isEmpty;
    private int whichEdge;
}
