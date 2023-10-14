package nitish.doctorbookingapp.controller;

import nitish.doctorbookingapp.model.Patient;
import nitish.doctorbookingapp.model.dto.AuthenticationInputDto;
import nitish.doctorbookingapp.model.dto.ScheduleAppointmentDto;
import nitish.doctorbookingapp.model.dto.SignInInputDto;
import nitish.doctorbookingapp.service.AppointmentController;
import nitish.doctorbookingapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Validated
@RestController
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    AppointmentController appointmentController;

     //API method to signUp
    @PostMapping("patient/signUp")
    public String patientSignup(@RequestBody Patient patient) {
        return patientService.patientSignUp(patient);
    }
     //API to signIn after signup which will give a token to acces other restricted api
    @PostMapping("patient/signIn")
    public String patientSignIn(@RequestBody SignInInputDto signInInput) {
        return patientService.patientSignIn(signInInput);
    }
     //API for signOut
    @DeleteMapping("pataient/signOut")
    public String patientSignOut(@RequestBody AuthenticationInputDto authenticationInput) {
        return patientService.patientSignOut(authenticationInput);
    }
    //API to book appointment
    //sending body using DTO "data to object".
    @PostMapping("patient/appointment/schedule")
    public String bookAppointment(@RequestBody ScheduleAppointmentDto scheduleAppointmentinfo) {
        return appointmentController.bookAppointment(scheduleAppointmentinfo.getAuthenticationInput(), scheduleAppointmentinfo.getAppointment());
    }
    //API to cancel the appointment by ID
    @DeleteMapping("patient/appointment/cancel/{appointmentId}")
    public String cancelAppointment(@RequestBody AuthenticationInputDto authenticationInput, @PathVariable Long appointmentId) {
        return appointmentController.cancelAppointment(authenticationInput, appointmentId);
    }

}
