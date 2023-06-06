package com.catan.repository;

import com.catan.model.board.Edge;
import com.catan.model.board.Field;
import com.catan.model.board.Vertex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.VetoableChangeListener;
import java.util.List;

/**
 * Repository to save Field objects.
 */
public interface FieldRepository extends JpaRepository<Field, Integer> {

    /**
     * Returns a list all the fields that contain the specified number as their diceNumber.
     * @param number dice number contained by the fields the method is looking for
     * @return list of fields containing the number
     */
    List<Field> findAllByDiceNumber(int number);

    /**
     * Returns a list containing all the fields that contain the specified edge.
     * @param edge edge contained by the fields the method is looking for
     * @return list of fields containing the edge
     */
    List<Field> findAllByEdgesContains(Edge edge);

    /**
     * Returns a list containing all the fields that contain the specified vertex.
     * @param vertex vertex contained by the fields the method is looking for
     * @return list of fields containing the vertex
     */
    List<Field> findAllByVerticesContains(Vertex vertex);

    /**
     * Returns the field associated to the specified id.
     * @param fieldId id of the field
     * @return field with the specified id
     */
    Field findById(int fieldId);
}
