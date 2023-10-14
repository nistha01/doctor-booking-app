package nitish.doctorbookingapp.repo;

import nitish.doctorbookingapp.model.PatientAuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPTokenRepo extends JpaRepository<PatientAuthenticationToken,Integer> {

    PatientAuthenticationToken findFirstByTokenValue(String tokenVlue);
}
