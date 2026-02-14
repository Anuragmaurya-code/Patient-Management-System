package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDT0;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.validators.CreatePatientValidationGroup;
import com.pm.patientservice.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientContoller {
    private final PatientService patientService;
    public PatientContoller(PatientService patientService){this.patientService=patientService;}

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatient(){
        List<PatientResponseDTO> patients= patientService.getPatient();
        return ResponseEntity.ok().body(patients);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDT0 patient){
        PatientResponseDTO newpatient=patientService.createPatient(patient);
        return ResponseEntity.ok().body(newpatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,@Validated({Default.class}) @RequestBody PatientRequestDT0 patientRequestDT0){
        PatientResponseDTO updatePatient= patientService.updatePatient(id, patientRequestDT0);
        return ResponseEntity.ok().body(updatePatient);
    }
}
