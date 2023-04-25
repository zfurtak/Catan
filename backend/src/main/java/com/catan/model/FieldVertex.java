package com.catan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FieldVertex")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FieldVertex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isEmpty;
    private int whichVertex;
}
