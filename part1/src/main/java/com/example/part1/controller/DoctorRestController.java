package com.example.part1.controller;

import com.example.part1.domain.Appointments;
import com.example.part1.domain.Doctor;
import com.example.part1.repo.AppointmentsRepo;
import com.example.part1.repo.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorRestController {

    @Autowired
    private DoctorRepo doctorRepository;

    @Autowired
    private AppointmentsRepo appointmentRepository;

    // Create a new doctor
    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        if (doctor == null) {
            return ResponseEntity.badRequest().build();
        }
        Doctor createdDoctor = doctorRepository.save(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDoctor);
    }

    // List all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Retrieve a specific doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElse(null);
        if (doctor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(doctor);
    }

    // Update a specific doctor by ID
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor updatedDoctor) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        if (updatedDoctor == null) {
            return ResponseEntity.badRequest().build();
        }
        existingDoctor.setName(updatedDoctor.getName());
        existingDoctor.setSpecialisation(updatedDoctor.getSpecialisation());
        existingDoctor.setEmail(updatedDoctor.getEmail());
        existingDoctor.setPhoneNumber(updatedDoctor.getPhoneNumber());

        Doctor savedDoctor = doctorRepository.save(existingDoctor);
        return ResponseEntity.ok(savedDoctor);
    }

    // Delete a specific doctor by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElse(null);
        if (doctor == null) {
            return ResponseEntity.notFound().build();
        }
        appointmentRepository.deleteAll(doctor.getAppointments());
        doctorRepository.delete(doctor);
        return ResponseEntity.noContent().build();
    }

    // List all appointments for a specific doctor
    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<Appointments>> getAppointmentsForDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElse(null);
        if (doctor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(doctor.getAppointments());
    }
}
