package com.example.courseregistration.controller;

import com.example.courseregistration.model.Course;
import com.example.courseregistration.service.CourseService;
import com.example.courseregistration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private RegistrationService registrationService;

    // GET /api/courses — return all courses (already working)
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // GET /api/courses/popular — return courses that have at least one registration
    @GetMapping("/popular")
    public List<Course> getPopularCourses() {
        return registrationService.getPopularCourses();
    }
}
