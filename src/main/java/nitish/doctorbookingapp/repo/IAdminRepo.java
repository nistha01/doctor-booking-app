package nitish.doctorbookingapp.repo;

import nitish.doctorbookingapp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin,Long> {
}
