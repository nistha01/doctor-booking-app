package nitish.doctorbookingapp.service;

import nitish.doctorbookingapp.model.Patient;
import nitish.doctorbookingapp.model.PatientAuthenticationToken;
import nitish.doctorbookingapp.model.dto.AuthenticationInputDto;
import nitish.doctorbookingapp.model.dto.SignInInputDto;
import nitish.doctorbookingapp.repo.IPatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    IPatientRepo iPatientRepo;
    @Autowired
    PTokenService pTokenService;
    //by using patient email and password doing signUp
    public String patientSignUp(Patient patient) {
        String email = patient.getPatientEmail();
        Patient curPatient = iPatientRepo.findFirstByPatientEmail(email);
        //if email already exists in database means, user is already signedUp and return
        if (curPatient != null) {
            return "Already signed up";
        }
        //If not signedUp then begin process of signing up
        // get password and encode password
        //save that to database
        String password = patient.getPatientPassword();
        try {
            String encryptedPassword = PasswordEncoder.encrypt(password);
            patient.setPatientPassword(encryptedPassword);
            iPatientRepo.save(patient);
            return "signed up";
            //if we pass wrong algirithm od encoding password then return error while saving
        } catch (NoSuchAlgorithmException e) {
            return "internal server issue while saving password";
        }

    }
    //signIn method
    public String patientSignIn(SignInInputDto signInInput) {
        //checking if email exists or not?
        String mail = signInInput.getEmail();
        Patient existingPatient = iPatientRepo.findFirstByPatientEmail(mail);
        //if exists then return for signUp first
        if (existingPatient == null) {
            return "signup first";
        }
        //get password and encode that and compare with the saved encoded password
        //it will return a token to give access to other API's
        String password = signInInput.getPassword();
        try {
            String encryptPass = PasswordEncoder.encrypt(password);
            //encrypt password
            if (encryptPass.equals(existingPatient.getPatientPassword())) {
                PatientAuthenticationToken token = new PatientAuthenticationToken(existingPatient);
                pTokenService.createToken(token);
                EmailService.sendEmail(mail,"token for login",token.getTokenValue());
                return "check your email for token";

            } else {
                //if password or mail is wrong or doesn't match with the saved mail and password
                return "invalid credentials";
            }
            //if algirthm passed is wrong then throw error
        } catch (NoSuchAlgorithmException e) {
            return "internal server error while saving password";
        }


        //signIn is possible if email exist
        //password should match then sign in
    }
   //method for signout
    //patients which are already signed in can signout
    public String patientSignOut(AuthenticationInputDto authenticationInput) {
        //verify if user is signed in or not
        //if signed in then it should have token
        //and with help of token delete the token given for signIn
        if (pTokenService.authenticate(authenticationInput)) {
            String tokenVlue = authenticationInput.getTokenValue();
            pTokenService.deleteToken(tokenVlue);
            return "user signed out";
        } else {
            //if mail or password doesn't match then can't delete token
            return "access denied";
        }
    }
    //returning list od patients
    public List<Patient> getAllPatient() {
        return iPatientRepo.findAll();
    }


}
