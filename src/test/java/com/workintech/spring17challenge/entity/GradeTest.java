package com.workintech.spring17challenge.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GradeTest {

    @Test
    void testGradeCreationAndGetters() {
        
        Integer expectedCoefficient = 5;
        String expectedNote = "Excellent";
        Grade grade = new Grade(expectedCoefficient, expectedNote);

        
        assertEquals(expectedCoefficient, grade.getCoefficient());
        assertEquals(expectedNote, grade.getNote());
    }
}
