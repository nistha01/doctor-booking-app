package nitish.doctorbookingapp.repo;

import nitish.doctorbookingapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepo extends JpaRepository<Patient,Integer> {

    Patient findFirstByPatientEmail(String email);


}
