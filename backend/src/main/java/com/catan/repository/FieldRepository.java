package com.catan.repository;

import com.catan.model.board.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Integer> {

    public List<Field> findAllByDiceNumber(int number);
    public Field findById(int fieldId);
}
