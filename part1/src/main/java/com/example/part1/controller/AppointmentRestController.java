package com.example.part1.controller;

import com.example.part1.domain.Appointments;
import com.example.part1.domain.Record;
import com.example.part1.repo.AppointmentsRepo;
import com.example.part1.repo.RecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentRestController {

    @Autowired
    private AppointmentsRepo appointmentRepository;

    @Autowired
    private RecordRepo medicalRecordRepository;

    // Create a new appointment
    @PostMapping
    public ResponseEntity<Appointments> createAppointment(@RequestBody Appointments appointment) {
        Appointments createdAppointment = appointmentRepository.save(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
    }

    // List all appointments
    @GetMapping
    public List<Appointments> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Retrieve a specific appointment by IDf
    @GetMapping("/{id}")
    public ResponseEntity<Appointments> getAppointmentById(@PathVariable Long id) {
        Appointments appointment = appointmentRepository.findById(id)
                .orElse(null);
        if (appointment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appointment);
    }

    // Update a specific appointment by ID
    @PutMapping("/{id}")
    public ResponseEntity<Appointments> updateAppointment(@PathVariable Long id, @RequestBody Appointments updatedAppointment) {
        Appointments existingAppointment = appointmentRepository.findById(id)
                .orElse(null);
        if (existingAppointment == null) {
            return ResponseEntity.notFound().build();
        }
        existingAppointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
        existingAppointment.setStatus(updatedAppointment.getStatus());
        existingAppointment.setNotes(updatedAppointment.getNotes());

        Appointments savedAppointment = appointmentRepository.save(existingAppointment);
        return ResponseEntity.ok(savedAppointment);
    }

    // Delete a specific appointment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        Appointments appointment = appointmentRepository.findById(id)
                .orElse(null);

        if (appointment == null) {
            return ResponseEntity.notFound().build();
        }
        if (appointment.getMedicalRecord() != null) {
            medicalRecordRepository.delete(appointment.getMedicalRecord());
        }

        appointmentRepository.delete(appointment);
        return ResponseEntity.noContent().build();
    }

    // Retrieve the medical record for a specific appointment
    @GetMapping("/{id}/medical-record")
    public ResponseEntity<Record> getMedicalRecordForAppointment(@PathVariable Long id) {
        Appointments appointment = appointmentRepository.findById(id)
                .orElse(null);
        if (appointment == null) {
            return ResponseEntity.notFound().build();
        }
        Record medicalRecord = appointment.getMedicalRecord();
        if (medicalRecord == null) {
            throw new RuntimeException("No medical record found for this appointment");
        }
        return ResponseEntity.ok(medicalRecord);
    }
}