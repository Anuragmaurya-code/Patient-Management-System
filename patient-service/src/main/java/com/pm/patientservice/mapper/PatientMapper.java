package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDT0;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient){
        PatientResponseDTO patientDTO=new PatientResponseDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setName(patient.getName());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientDTO;
    }
    public static Patient toModel(PatientRequestDT0 patientRequestDT0){
        Patient patient=new Patient();
        patient.setName(patientRequestDT0.getName());
        patient.setAddress(patientRequestDT0.getAddress());
        patient.setEmail(patientRequestDT0.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDT0.getDateOfBirth()));
        patient.setRegisterDate(LocalDate.parse(patientRequestDT0.getDateOfRegister()));
        return patient;
    }
}
