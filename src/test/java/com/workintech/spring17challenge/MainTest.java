package com.workintech.spring17challenge;

import com.workintech.spring17challenge.entity.*;
import com.workintech.spring17challenge.exceptions.ApiErrorResponse;
import com.workintech.spring17challenge.exceptions.ApiException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @Test
    @DisplayName("Test Course Setters and Getters")
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

    @Test
    @DisplayName("Test Grade Creation and Getters")
    void testGradeCreationAndGetters() {

        Integer expectedCoefficient = 5;
        String expectedNote = "Excellent";
        Grade grade = new Grade(expectedCoefficient, expectedNote);


        assertEquals(expectedCoefficient, grade.getCoefficient());
        assertEquals(expectedNote, grade.getNote());
    }

    @Test
    @DisplayName("Test GetHighCourseGpa Returns Correct Value")
    void testGetGpaReturnsCorrectValue() {

        CourseGpa highCourseGpa = new HighCourseGpa();
        int gpa = highCourseGpa.getGpa();

        assertEquals(10, gpa, "HighCourseGpa should return a GPA of 10");
    }

    @Test
    @DisplayName("Test GetLowCourseGpa Returns Correct Value")
    void getGpa() {
        CourseGpa lowCourseGpa = new LowCourseGpa();
        assertEquals(3, lowCourseGpa.getGpa(), "LowCourseGpa should return a GPA of 3");
    }


    @Test
    @DisplayName("Test MediumGpa Returns Correct Value")
    void testMediumGpaReturnsCorrectValue() {
        CourseGpa mediumCourseGpa = new MediumCourseGpa();
        int gpa = mediumCourseGpa.getGpa();

        assertEquals(5, gpa, "MediumCourseGpa should return a GPA of 5");
    }

    @Test
    @DisplayName("Test ApiErrorResponse Fields")
    void testApiErrorResponseFields() {
        // Given
        Integer expectedStatus = 404;
        String expectedMessage = "Not Found";
        Long expectedTimestamp = System.currentTimeMillis();

        // When
        ApiErrorResponse errorResponse = new ApiErrorResponse(expectedStatus, expectedMessage, expectedTimestamp);

        // Then
        assertEquals(expectedStatus, errorResponse.getStatus(), "The status should match the expected value.");
        assertEquals(expectedMessage, errorResponse.getMessage(), "The message should match the expected value.");
        assertEquals(expectedTimestamp, errorResponse.getTimestamp(), "The timestamp should match the expected value.");
    }


    @Test
    void testZooExceptionCreation() {
        String expectedMessage = "Test exception message";
        HttpStatus expectedStatus = HttpStatus.NOT_FOUND;

        ApiException exception = new ApiException(expectedMessage, expectedStatus);


        assertEquals(expectedMessage, exception.getMessage(), "The exception message should match the expected value.");
        assertEquals(expectedStatus, exception.getHttpStatus(), "The HttpStatus should match the expected value.");


        assertTrue(exception instanceof RuntimeException, "ZooException should be an instance of RuntimeException.");
    }

    @Test
    void testHttpStatusSetter() {
        ApiException exception = new ApiException("Initial message", HttpStatus.OK);
        exception.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);


        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus(), "The HttpStatus should be updatable and match the new value.");
    }
}
