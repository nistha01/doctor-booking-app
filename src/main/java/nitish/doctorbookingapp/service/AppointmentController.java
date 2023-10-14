package nitish.doctorbookingapp.service;

import nitish.doctorbookingapp.model.Appointment;
import nitish.doctorbookingapp.model.Patient;
import nitish.doctorbookingapp.model.dto.AuthenticationInputDto;
import nitish.doctorbookingapp.repo.IAppointmentRepo;
import nitish.doctorbookingapp.repo.IDoctorRepo;
import nitish.doctorbookingapp.repo.IPatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentController {

    @Autowired
    IAppointmentRepo iAppointmentRepo;
    @Autowired
    PTokenService pTokenService;

    @Autowired
    IPatientRepo iPatientRepo;

    @Autowired
    IDoctorRepo iDoctorRepo;

   // method to book an appointment
    public String bookAppointment(AuthenticationInputDto authenticationInput, Appointment appointment) {
        //patient who has authentiation token they can only book any appointment
        //calling authenticate method to verify patient
        if (pTokenService.authenticate(authenticationInput)) {
            //get email from passed parameter authenticate input
            String mail = authenticationInput.getEmail();
            //using email find patient
            Patient patient = iPatientRepo.findFirstByPatientEmail(mail);
            //set appointment
            appointment.setPatient(patient);
           //get doctor ID which has to be set in appointment
            Integer docId = appointment.getDoctor().getDocId();
            //check if doctor exists or not by parameter passed doctor id
            //we are taking doctor id from patient that which doctor he/she wants to book
            boolean isDoctor = iDoctorRepo.existsById(docId);
            //if docotr is available then set the time and create appointment
            if (isDoctor) {
                appointment.setAppCreationTime(LocalDateTime.now());
                iAppointmentRepo.save(appointment);
                return "apppointment confirmed at" + appointment.getAppCreationTime() + "with Dr." + appointment.getDoctor().getDocName();
            } else {
                return "doctor doesn't exists";//return that doctor doesn't exists
            }

        }
        //if patient doesn't have authentication token then deny the access to book any appointment

        return "access denied";

    }
    //method to cancel an appointment
    public String cancelAppointment(AuthenticationInputDto authenticationInput, Long appointmentId) {
        //verify the user by token
        //if he/she has valid token then take further inside the method
        if (pTokenService.authenticate(authenticationInput)) {
            //get mailID
            String email = authenticationInput.getEmail();
            Patient curPatient = iPatientRepo.findFirstByPatientEmail(email);
            //find patient and then get the appointment booked already
            //if appointment is found then take inside the methode or else
            Appointment exixtingAppointment = iAppointmentRepo.findById(appointmentId).orElse(null);
            //delete the appointment
            if (curPatient.equals(exixtingAppointment.getPatient())) {
                iAppointmentRepo.deleteById(appointmentId);
                return "appointment with dr." + exixtingAppointment.getDoctor().getDocName() + "has been cancelled";
            }
            //or else unauthorised access
            //which means patient have valid token but doesn't have any appointment
            return "UnAuthorised access";

        }
        //not allowed because patient doesn't have access inside and patient doesn't have token
        return "Unauthenticated access";
    }
}
