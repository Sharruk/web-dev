package com.example.courseregistration.repository;

import com.example.courseregistration.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {

    // Find students by department
    List<Student> findByDepartment(String department);

    // Find a student by their studentId field
    Student findByStudentId(String studentId);
}
