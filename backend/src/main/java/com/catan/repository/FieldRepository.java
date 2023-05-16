package com.catan.repository;

import com.catan.model.board.Edge;
import com.catan.model.board.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Integer> {

    List<Field> findAllByDiceNumber(int number);

    List<Field> findAllByEdgesContains(Edge edge);


    Field findById(int fieldId);
}
