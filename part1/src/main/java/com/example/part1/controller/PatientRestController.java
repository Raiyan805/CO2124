package com.example.part1.controller;

import com.example.part1.domain.Appointments;
import com.example.part1.domain.Patient;
import com.example.part1.domain.Record;
import com.example.part1.repo.AppointmentsRepo;
import com.example.part1.repo.PatientRepo;
import com.example.part1.repo.RecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientRestController {

    @Autowired
    private PatientRepo patientRepository;

    @Autowired
    private AppointmentsRepo appointmentRepository;

    @Autowired
    private RecordRepo medicalRecordRepository;

    // Create a new patient
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        if (patient == null) {
            return ResponseEntity.badRequest().build();
        }
        Patient createdPatient = patientRepository.save(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    // List all patients
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Retrieve a specific patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id)
                .orElse(null);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patient);
    }

    // Update a specific patient by ID
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        if (updatedPatient == null) {
            return ResponseEntity.badRequest().build();
        }
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        existingPatient.setName(updatedPatient.getName());
        existingPatient.setEmail(updatedPatient.getEmail());
        existingPatient.setPhoneNumber(updatedPatient.getPhoneNumber());
        existingPatient.setAddress(updatedPatient.getAddress());

        Patient savedPatient = patientRepository.save(existingPatient);
        return ResponseEntity.ok(savedPatient);
    }

    // Delete a specific patient by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id)
                .orElse(null);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }
        // Cascade delete appointments (due to orphanRemoval=true)
        for (Appointments appointment : patient.getAppointments()) {
            if (appointment.getMedicalRecord() != null) {
                medicalRecordRepository.delete(appointment.getMedicalRecord());
            }
            appointmentRepository.delete(appointment);
        }

        patientRepository.delete(patient);
        return ResponseEntity.noContent().build();
    }

    // List all appointments for a specific patient
    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<Appointments>> getAppointmentsForPatient(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id)
                .orElse(null);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patient.getAppointments());
    }

    // List all medical records for a specific patient
    @GetMapping("/{id}/medical-records")
    public ResponseEntity<List<Record>> getMedicalRecordsForPatient(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id)
                .orElse(null);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }
        List<Record> medicalRecords = new ArrayList<>();
        for (Appointments appointment : patient.getAppointments()) {
            if (appointment.getMedicalRecord() != null) {
                medicalRecords.add(appointment.getMedicalRecord());
            }
        }
        return ResponseEntity.ok(medicalRecords);
    }
}
