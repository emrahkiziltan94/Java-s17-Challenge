package com.workintech.spring17challenge.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MediumCourseGpaTest {

    @Test
    void testMediumGpaReturnsCorrectValue() {
        CourseGpa mediumCourseGpa = new MediumCourseGpa();
        int gpa = mediumCourseGpa.getGpa();

        assertEquals(5, gpa, "MediumCourseGpa should return a GPA of 5");
    }
}
