package com.workintech.spring17challenge.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiErrorResponseTest {

    @Test
    public void testApiErrorResponseFields() {
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
}
