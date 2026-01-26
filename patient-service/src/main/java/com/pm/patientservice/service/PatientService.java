package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDT0;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Patient newPatient=patientRepository.save(PatientMapper.toModel(patientRequestDT0));
        return PatientMapper.toDTO(newPatient);

    }
}
