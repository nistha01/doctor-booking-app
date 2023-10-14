package nitish.doctorbookingapp.repo;

import nitish.doctorbookingapp.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepo extends JpaRepository<Appointment,Long> {
}
