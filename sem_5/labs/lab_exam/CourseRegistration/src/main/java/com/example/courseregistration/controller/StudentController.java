package com.example.courseregistration.controller;

import com.example.courseregistration.model.Course;
import com.example.courseregistration.model.Registration;
import com.example.courseregistration.model.Student;
import com.example.courseregistration.service.RegistrationService;
import com.example.courseregistration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private StudentService studentService;

    // GET /api/students/{studentId}/courses — courses registered by a student
    @GetMapping("/students/{studentId}/courses")
    public List<Course> getCoursesByStudent(@PathVariable String studentId) {
        return registrationService.getCoursesByStudent(studentId);
    }

    // GET /api/registrations/department/{department} — registrations by student department
    @GetMapping("/registrations/department/{department}")
    public List<Registration> getRegistrationsByDepartment(@PathVariable String department) {
        return studentService.getRegistrationsByDepartment(department);
    }

    // GET /api/students/multiple-courses — students registered in more than one course
    @GetMapping("/students/multiple-courses")
    public List<Student> getStudentsWithMultipleCourses() {
        return studentService.getStudentsWithMultipleCourses();
    }

    // GET /api/students/{studentId}/credits — total credits for a student
    @GetMapping("/students/{studentId}/credits")
    public Map<String, Object> getTotalCredits(@PathVariable String studentId) {
        int total = studentService.getTotalCreditsByStudent(studentId);
        Map<String, Object> response = new HashMap<>();
        response.put("studentId", studentId);
        response.put("totalCredits", total);
        return response;
    }
}
