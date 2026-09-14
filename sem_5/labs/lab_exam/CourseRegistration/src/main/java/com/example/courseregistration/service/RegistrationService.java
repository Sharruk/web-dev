package com.example.courseregistration.service;

import com.example.courseregistration.model.Course;
import com.example.courseregistration.model.Registration;
import com.example.courseregistration.repository.CourseRepository;
import com.example.courseregistration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Save a new registration to MongoDB
    public Registration registerCourse(Registration registration) {
        return registrationRepository.save(registration);
    }

    // Delete a registration by its registrationId field
    @Transactional
    public void deleteRegistration(String registrationId) {
        registrationRepository.deleteByRegistrationId(registrationId);
    }

    // Return courses registered by a student
    public List<Course> getCoursesByStudent(String studentId) {
        // Step 1: Get all registrations for this student
        List<Registration> registrations = registrationRepository.findByStudentId(studentId);

        // Step 2: For each registration, find the matching course by courseId
        List<Course> courses = new ArrayList<>();
        for (Registration reg : registrations) {
            List<Course> allCourses = courseRepository.findAll();
            for (Course course : allCourses) {
                if (course.getCourseId().equals(reg.getCourseId())) {
                    courses.add(course);
                    break;
                }
            }
        }
        return courses;
    }

    // Return courses that have at least one registration (popular courses)
    public List<Course> getPopularCourses() {
        List<Course> allCourses = courseRepository.findAll();
        List<Course> popularCourses = new ArrayList<>();

        for (Course course : allCourses) {
            // Check if this course has any registration
            if (registrationRepository.existsByCourseId(course.getCourseId())) {
                popularCourses.add(course);
            }
        }
        return popularCourses;
    }
}
