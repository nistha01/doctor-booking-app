package nitish.doctorbookingapp.service;

import nitish.doctorbookingapp.model.PatientAuthenticationToken;
import nitish.doctorbookingapp.model.dto.AuthenticationInputDto;
import nitish.doctorbookingapp.repo.IPTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PTokenService {
    @Autowired
    IPTokenRepo ipTokenRepo;

   //saving a token
    public void createToken(PatientAuthenticationToken token) {
        ipTokenRepo.save(token);
    }
    //deleting a token
    public void deleteToken(String tokenVlue) {

        PatientAuthenticationToken token = ipTokenRepo.findFirstByTokenValue(tokenVlue);
        ipTokenRepo.delete(token);

    }
    //authenticate a token
    public boolean authenticate(AuthenticationInputDto authenticationInput) {
        //get email
        String email = authenticationInput.getEmail();
        //get token value
        String tokenValue = authenticationInput.getTokenValue();
        //find token by using token value;
        PatientAuthenticationToken token = ipTokenRepo.findFirstByTokenValue(tokenValue);
        //if !null then yes "patient is authenticated by sending true boolean"
        if (token != null) {
            return token.getPatient().getPatientEmail().equals(email);
        } else {
            return false;  //else false
        }
    }
}
