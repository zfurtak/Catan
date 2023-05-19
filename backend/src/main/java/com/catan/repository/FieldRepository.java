package com.catan.repository;

import com.catan.model.board.Edge;
import com.catan.model.board.Field;
import com.catan.model.board.Vertex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.VetoableChangeListener;
import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Integer> {

    List<Field> findAllByDiceNumber(int number);

    List<Field> findAllByEdgesContains(Edge edge);

    List<Field> findAllByVerticesContains(Vertex vertex);

    Field findById(int fieldId);
}
