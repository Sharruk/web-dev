package com.example.courseregistration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseRegistrationApiTest {

    @Autowired
    private MockMvc mockMvc;

    // Test 1: GET /api/courses — must return 200 OK
    @Test
    public void testGetAllCourses() throws Exception {
        mockMvc.perform(get("/api/courses"))
                .andExpect(status().isOk());
    }

    // Test 2: POST /api/registrations — save a registration
    @Test
    public void testPostRegistration() throws Exception {
        String registrationJson = "{"
                + "\"registrationId\": \"R999\","
                + "\"studentId\": \"S101\","
                + "\"courseId\": \"C101\","
                + "\"semester\": 5,"
                + "\"academicYear\": \"2026-27\""
                + "}";

        mockMvc.perform(post("/api/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registrationJson))
                .andExpect(status().isOk());
    }

    // Test 3: GET /api/students/S101/courses — courses registered by S101
    @Test
    public void testGetCoursesByStudent() throws Exception {
        mockMvc.perform(get("/api/students/S101/courses"))
                .andExpect(status().isOk());
    }

    // Test 4: DELETE /api/registrations/R999 — delete the registration we just created
    @Test
    public void testDeleteRegistration() throws Exception {
        // First insert a registration so we can delete it
        String registrationJson = "{"
                + "\"registrationId\": \"R998\","
                + "\"studentId\": \"S101\","
                + "\"courseId\": \"C101\","
                + "\"semester\": 5,"
                + "\"academicYear\": \"2026-27\""
                + "}";

        mockMvc.perform(post("/api/registrations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registrationJson))
                .andExpect(status().isOk());

        // Now delete it
        mockMvc.perform(delete("/api/registrations/R998"))
                .andExpect(status().isOk());
    }

    // Test 5: GET /api/courses/popular — must return 200 OK
    @Test
    public void testGetPopularCourses() throws Exception {
        mockMvc.perform(get("/api/courses/popular"))
                .andExpect(status().isOk());
    }

    // Test 6: GET /api/registrations/department/CSE — must return 200 OK
    @Test
    public void testGetRegistrationsByDepartment() throws Exception {
        mockMvc.perform(get("/api/registrations/department/CSE"))
                .andExpect(status().isOk());
    }

    // Test 7: GET /api/students/multiple-courses — must return 200 OK
    @Test
    public void testGetStudentsWithMultipleCourses() throws Exception {
        mockMvc.perform(get("/api/students/multiple-courses"))
                .andExpect(status().isOk());
    }

    // Test 8: GET /api/students/S101/credits — must return 200 OK with studentId in response
    @Test
    public void testGetStudentTotalCredits() throws Exception {
        mockMvc.perform(get("/api/students/S101/credits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId").value("S101"));
    }

    // Test 9: Negative test — a non-existing path must return 404
    @Test
    public void testNonExistingPathReturns404() throws Exception {
        mockMvc.perform(get("/api/nonexistent-path"))
                .andExpect(status().isNotFound());
    }
}
