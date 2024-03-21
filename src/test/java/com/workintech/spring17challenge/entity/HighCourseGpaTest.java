package com.workintech.spring17challenge.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

 class HighCourseGpaTest {

    @Test
     void testGetGpaReturnsCorrectValue() {
        
        CourseGpa highCourseGpa = new HighCourseGpa();
        int gpa = highCourseGpa.getGpa();

        assertEquals(10, gpa, "HighCourseGpa should return a GPA of 10");
    }
}
