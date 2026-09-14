package com.example.courseregistration.repository;

import com.example.courseregistration.model.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RegistrationRepository extends MongoRepository<Registration, String> {

    // Get all registrations for a specific student
    List<Registration> findByStudentId(String studentId);

    // Find a registration by registrationId field
    Registration findByRegistrationId(String registrationId);

    // Delete a registration by registrationId field
    void deleteByRegistrationId(String registrationId);

    // Check if a courseId has any registrations
    boolean existsByCourseId(String courseId);
}
