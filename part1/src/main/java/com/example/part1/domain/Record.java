package com.example.part1.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime recordDate;
    private String diagnosis;
    private String treatment;
    private String notes;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    @JsonManagedReference("record-appointments")
    private Appointments appointment;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for recordDate
    public LocalDateTime getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate) {
        this.recordDate = recordDate;
    }

    // Getter and Setter for diagnosis
    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    // Getter and Setter for treatment
    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    // Getter and Setter for notes
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Getter and Setter for appointment
    public Appointments getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointments appointment) {
        this.appointment = appointment;
    }
}
