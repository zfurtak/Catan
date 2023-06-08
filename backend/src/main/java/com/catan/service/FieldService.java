package com.catan.service;

import com.catan.model.board.Edge;
import com.catan.model.board.Field;
import com.catan.model.board.Vertex;
import com.catan.repository.FieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for the Field class.
 *  @author Zuzanna Furtak
 *  @author Agnieszka Lasek
 */
@Service
public class FieldService {
    private final FieldRepository fieldRepository;

    /**
     * Initialize the service.
     * @param fieldRepository
     */
    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    /**
     * Returns a list all the fields that contain the specified number as their diceNumber.
     * @param number dice number contained by the fields the method is looking for
     * @return list of fields containing the number
     */
    public List<Field> findAllByDiceNumber(int number) {
        return this.fieldRepository.findAllByDiceNumber(number);
    }

    /**
     * Unblocks the thief from the field it is currently in.
     */
    public void unlockPrevThief() {
        Field oldThiefField = fieldRepository.findAll().stream().filter(Field::isBlocked).toList().get(0);
        oldThiefField.setBlocked(false);
        fieldRepository.save(oldThiefField);
    }

    /**
     * Blocks the thief in the specified field.
     * @param field field where the thief is blocked
     */
    public void blockNewThief(Field field) {
        // TODO: Check if this is correct -> put thief on the field chosen by player
        // should work, idk
        Field newThiefField = fieldRepository.findById(field.getId());
        newThiefField.setBlocked(true);
        fieldRepository.save(newThiefField);
    }

    /**
     * Returns a list containing all the fields that contain the specified edge.
     * @param edge edge contained by the fields the method is looking for
     * @return list of fields containing the edge
     */
    public List<Field> getFieldsByEdge(Edge edge){
        return fieldRepository.findAllByEdgesContains(edge);
    }

    /**
     * Returns a list containing all the fields that contain the specified vertex.
     * @param vertex vertex contained by the fields the method is looking for
     * @return list of fields containing the vertex
     */
    public List<Field> getFieldsByVertex(Vertex vertex) { 
        return fieldRepository.findAllByVerticesContains(vertex); 
    }

}
