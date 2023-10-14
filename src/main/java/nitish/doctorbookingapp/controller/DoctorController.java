package nitish.doctorbookingapp.controller;

import nitish.doctorbookingapp.model.Doctor;
import nitish.doctorbookingapp.model.Patient;
import nitish.doctorbookingapp.model.dto.AuthenticationInputDto;
import nitish.doctorbookingapp.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Validated
@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    //doctor have access to see all doctors
    @GetMapping("doctors")
    public List<Doctor> getAllDoctors(@RequestBody AuthenticationInputDto authenticationInput) {
        return doctorService.getAllDoctors(authenticationInput);
    }

    //doctor can see their data by their ID, if docotor is already added
    @GetMapping("doctor/id/{id}")
    public Doctor getDoctor(@RequestBody Integer id) {
        return doctorService.getDoctorByID(id);
    }


}
