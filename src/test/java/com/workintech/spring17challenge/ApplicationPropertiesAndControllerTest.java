package com.workintech.spring17challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.spring17challenge.entity.Course;
import com.workintech.spring17challenge.entity.Grade;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class ApplicationPropertiesAndControllerTest {

    @Autowired
    private Environment env;


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
    @DisplayName("application properties istenilenler eklendi mi?")
    void serverPortIsSetTo8585() {

        String serverPort = env.getProperty("server.port");
        assertThat(serverPort).isEqualTo("9000");

        String contextPath = env.getProperty("server.servlet.context-path");
        assertNotNull(contextPath);
        assertThat(contextPath).isEqualTo("/workintech");

    }

    @Test
    void testHandleApiException() throws Exception {

        String nonExistingName = "testCourseName";

        mockMvc.perform(get("/courses/{name}", nonExistingName))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    void testCreateCourseValidationFailure() throws Exception {
        Course invalidCourse = new Course(null, null, null, null); // Assuming this will fail validation

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidCourse)))
                .andExpect(status().isBadRequest()) // Expecting validation failure
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()));
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


