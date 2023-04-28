package com.catan.service;

import com.catan.model.board.Field;
import com.catan.model.board.Vertex;
import com.catan.repository.FieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {
    private final FieldRepository fieldRepository;


    public FieldService(FieldRepository fieldRepository){
        this.fieldRepository = fieldRepository;
    }

    public List<Field> findAllByDiceNumber(int number){
        return this.fieldRepository.findAllByDiceNumber(number);
    }
}
