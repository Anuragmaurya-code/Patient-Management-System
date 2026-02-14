package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDT0;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.EmailAlreadyExistsException;
import com.pm.patientservice.exception.PatientNotFoundException;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import jakarta.validation.constraints.Email;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private PatientRepository patientRepository;
    public  PatientService(PatientRepository patientRepository){this.patientRepository=patientRepository;} // patient repository

    public List<PatientResponseDTO> getPatient(){
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponseDTO> patientResponseDTO=patients.stream().map( patient -> PatientMapper.toDTO(patient)).toList();
//        System.out.println("patients.stream().map( patient -> PatientMapper.toDTO(patient))  "+patients.stream().map( patient -> PatientMapper.toDTO(patient)));
        return patientResponseDTO;

    }

    public PatientResponseDTO createPatient(PatientRequestDT0 patientRequestDT0){
        if(patientRepository.existsByEmail(patientRequestDT0.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email "+"already exists " +patientRequestDT0.getEmail());
        }
        Patient newPatient=patientRepository.save(PatientMapper.toModel(patientRequestDT0));
        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(UUID id,PatientRequestDT0 patientRequestDT0){
        // 2 validation - for email and other for if id exists
        Patient patient=patientRepository.findById(id).orElseThrow(
                ()-> new PatientNotFoundException("Patient not found with id"+id));
        if(patientRepository.existsByEmailAndIdNot(patientRequestDT0.getEmail(),id)){
                throw new EmailAlreadyExistsException("A patient with this email " + "already exists " + patientRequestDT0.getEmail());

        }

        patient.setEmail(patientRequestDT0.getEmail());
        patient.setName(patientRequestDT0.getName());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDT0.getDateOfBirth()));
        patient.setAddress(patientRequestDT0.getAddress());

        Patient updatedPatient=patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }
}
