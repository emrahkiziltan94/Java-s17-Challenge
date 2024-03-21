package com.workintech.spring17challenge.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest {

    @Test
    void testCourseSettersAndGetters() {
        
        Course course = new Course();
        Grade grade = new Grade(5, "Excellent");

        
        Integer expectedId = 101;
        String expectedName = "Advanced Mathematics";
        Integer expectedCredit = 4;

        course.setId(expectedId);
        course.setName(expectedName);
        course.setCredit(expectedCredit);
        course.setGrade(grade);

        
        assertEquals(expectedId, course.getId());
        assertEquals(expectedName, course.getName());
        assertEquals(expectedCredit, course.getCredit());
        assertEquals(grade, course.getGrade());
    }
}
