package nitish.doctorbookingapp.service;

import nitish.doctorbookingapp.repo.IAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    IAdminRepo iAdminRepo;
}
