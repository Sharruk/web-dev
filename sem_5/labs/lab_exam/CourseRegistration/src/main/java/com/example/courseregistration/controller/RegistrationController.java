package com.example.courseregistration.controller;

import com.example.courseregistration.model.Course;
import com.example.courseregistration.model.Registration;
import com.example.courseregistration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    // POST /api/registrations — save a new registration
    @PostMapping
    public Registration registerCourse(@RequestBody Registration registration) {
        return registrationService.registerCourse(registration);
    }

    // DELETE /api/registrations/{registrationId} — remove a registration by registrationId
    @DeleteMapping("/{registrationId}")
    public String deleteRegistration(@PathVariable String registrationId) {
        registrationService.deleteRegistration(registrationId);
        return "Registration " + registrationId + " deleted successfully";
    }
}
