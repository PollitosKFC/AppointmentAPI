package pe.edu.upc.appointmentapi.EasyJobs.service;

import org.springframework.stereotype.Service;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Technician;

import java.util.List;
@Service
public interface TechnicianService {
    Technician createTechnician(Technician technician);
    List<Technician> getAllTechnicians();
}
