package nitish.doctorbookingapp.repo;

import nitish.doctorbookingapp.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepo extends JpaRepository<Doctor,Integer> {
}
