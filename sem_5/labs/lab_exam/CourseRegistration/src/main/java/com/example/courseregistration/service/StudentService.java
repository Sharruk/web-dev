package com.example.courseregistration.service;

import com.example.courseregistration.model.Course;
import com.example.courseregistration.model.Registration;
import com.example.courseregistration.model.Student;
import com.example.courseregistration.repository.CourseRepository;
import com.example.courseregistration.repository.RegistrationRepository;
import com.example.courseregistration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Return registrations belonging to students from a given department
    public List<Registration> getRegistrationsByDepartment(String department) {
        // Step 1: Find all students in that department
        List<Student> students = studentRepository.findByDepartment(department);

        // Step 2: Collect their studentIds
        List<String> studentIds = new ArrayList<>();
        for (Student student : students) {
            studentIds.add(student.getStudentId());
        }

        // Step 3: Find all registrations whose studentId is in that list
        List<Registration> result = new ArrayList<>();
        List<Registration> allRegistrations = registrationRepository.findAll();
        for (Registration reg : allRegistrations) {
            if (studentIds.contains(reg.getStudentId())) {
                result.add(reg);
            }
        }
        return result;
    }

    // Return students who have registered for more than one course
    public List<Student> getStudentsWithMultipleCourses() {
        List<Registration> allRegistrations = registrationRepository.findAll();

        // Count how many courses each student is registered for
        Map<String, Integer> countMap = new HashMap<>();
        for (Registration reg : allRegistrations) {
            String sid = reg.getStudentId();
            countMap.put(sid, countMap.getOrDefault(sid, 0) + 1);
        }

        // Collect studentIds that have more than 1 course
        List<String> multiStudentIds = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > 1) {
                multiStudentIds.add(entry.getKey());
            }
        }

        // Fetch the Student objects for those studentIds
        List<Student> result = new ArrayList<>();
        List<Student> allStudents = studentRepository.findAll();
        for (Student student : allStudents) {
            if (multiStudentIds.contains(student.getStudentId())) {
                result.add(student);
            }
        }
        return result;
    }

    // Return the total credits earned by a student across all registered courses
    public int getTotalCreditsByStudent(String studentId) {
        // Step 1: Get all registrations for this student
        List<Registration> registrations = registrationRepository.findByStudentId(studentId);

        // Step 2: Sum up credits from the corresponding courses
        int totalCredits = 0;
        List<Course> allCourses = courseRepository.findAll();
        for (Registration reg : registrations) {
            for (Course course : allCourses) {
                if (course.getCourseId().equals(reg.getCourseId())) {
                    totalCredits += course.getCredits();
                    break;
                }
            }
        }
        return totalCredits;
    }
}
