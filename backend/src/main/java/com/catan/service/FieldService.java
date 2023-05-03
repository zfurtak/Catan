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

    public void unlockPrevThief(){
        Field oldThiefField = fieldRepository.findAll().stream().filter(Field::isBlocked).toList().get(0);
        oldThiefField.setBlocked(false);
        fieldRepository.save(oldThiefField);
    }

    public void blockNewThief(Field field){
        // TODO: Check if this is correct -> put thief on the field chosen by player
        // should work, idk
        Field newThiefField = fieldRepository.findById(field.getId());
        newThiefField.setBlocked(true);
        fieldRepository.save(newThiefField);
    }

}
