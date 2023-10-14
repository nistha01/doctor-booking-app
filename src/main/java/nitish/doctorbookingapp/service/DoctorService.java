package nitish.doctorbookingapp.service;

import nitish.doctorbookingapp.model.Doctor;
import nitish.doctorbookingapp.model.Patient;
import nitish.doctorbookingapp.model.dto.AuthenticationInputDto;
import nitish.doctorbookingapp.repo.IDoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    IDoctorRepo iDoctorRepo;
    @Autowired
    PTokenService pTokenService;
    //returning list of doctor
    //doctor have access of if docotor is authenticated
    public List<Doctor> getAllDoctors(AuthenticationInputDto authenticationInput) {
        if (pTokenService.authenticate(authenticationInput)) {
            return iDoctorRepo.findAll();
        }
        return null;
    }
    //admin adding doctor ,patients and doctor can't add doctor
    public String addDoctor(Doctor doctor) {
        Integer docId = doctor.getDocId();
        if (docId != null && iDoctorRepo.existsById(docId)) {
            return "doctor already exists";
        }
        doctor.setAppointments(null);
        iDoctorRepo.save(doctor);
        return "doctor added";

    }
     //returning any particular doctor data by using their ID
    public Doctor getDoctorByID(Integer id) {
        return iDoctorRepo.findById(id).orElse(null);
    }


}
