package com.workintech.spring17challenge.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LowCourseGpaTest {

    @Test
    void getGpa() {
        CourseGpa lowCourseGpa = new LowCourseGpa();
        assertEquals(3, lowCourseGpa.getGpa(), "LowCourseGpa should return a GPA of 3");
    }
}
