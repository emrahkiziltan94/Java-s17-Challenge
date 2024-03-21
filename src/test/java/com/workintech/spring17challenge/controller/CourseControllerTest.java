package com.workintech.spring17challenge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.spring17challenge.entity.Course;
import com.workintech.spring17challenge.entity.Grade;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Course course;

    @BeforeEach
    void setUp() throws Exception {
        // Setup a sample Course object
        course = new Course();
        course.setId(1);
        course.setName("Introduction to Spring");
        course.setCredit(3);
        course.setGrade(new Grade(1, "A"));

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(1)
    void testCreateCourse() throws Exception {
        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    void testGetAllCourses() throws Exception {
        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }

    @Test
    @Order(3)
    void testGetCourseByName() throws Exception {
        String courseName = course.getName();
        // Assuming course with 'courseName' has been added in `setUp` or via another test
        mockMvc.perform(get("/courses/{name}", courseName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(courseName)));
    }

    @Test
    @Order(4)
    void testUpdateCourse() throws Exception {
        course.setName("Advanced Spring");
        mockMvc.perform(put("/courses/{id}", course.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    void testDeleteCourse() throws Exception {
        // Assuming course with `course.getId()` exists
        mockMvc.perform(delete("/courses/{id}", course.getId()))
                .andExpect(status().isOk());
    }
}
