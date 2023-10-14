package nitish.doctorbookingapp.controller;

import nitish.doctorbookingapp.model.Doctor;
import nitish.doctorbookingapp.model.Patient;
import nitish.doctorbookingapp.service.DoctorService;
import nitish.doctorbookingapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Validated
@RestController
public class AdminController {

    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;
    //admin to add doctor
    @PostMapping("addDoctor")
    public String addDoctor(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }
    //admin can get all patients list
    @GetMapping("patients")
    public List<Patient> getAllPatient() {
        return patientService.getAllPatient();
    }
}
