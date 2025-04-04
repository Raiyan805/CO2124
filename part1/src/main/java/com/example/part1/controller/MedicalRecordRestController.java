package com.example.part1.controller;

import com.example.part1.repo.RecordRepo;
import com.example.part1.domain.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordRestController {

    @Autowired
    private RecordRepo medicalRecordRepository;

    // Create a new medical record
    @PostMapping
    public ResponseEntity<Record> createMedicalRecord(@RequestBody Record medicalRecord) {
        Record createdMedicalRecord = medicalRecordRepository.save(medicalRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMedicalRecord);
    }
}