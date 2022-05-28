package pe.edu.upc.appointmentapi.EasyJobs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Technician;
import pe.edu.upc.appointmentapi.EasyJobs.repository.TechnicianRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnicianServiceImpl implements TechnicianService {

    @Autowired
    private TechnicianRepository technicianRepository;

    @Override
    public Technician createTechnician(Technician technician) {
        Technician newTechnician = new Technician();
        newTechnician.setUserName(technician.getUserName());
        newTechnician.setRegisterDate(new Date());
        newTechnician.setUpdatedDate(new Date());
        newTechnician.setFirstName(technician.getFirstName());
        newTechnician.setLastName(technician.getLastName());
        newTechnician.setEmail(technician.getEmail());
        newTechnician.setVerified(technician.getVerified());
        newTechnician.setPassword(technician.getPassword());
        newTechnician.setPhoneNumber(technician.getPhoneNumber());
        newTechnician.setAddress(technician.getAddress());
        newTechnician.setCity(technician.getCity());
        newTechnician.setDistrict(technician.getDistrict());
        newTechnician.setGender(technician.getGender());
        newTechnician.setType("TECHNICIAN");
        newTechnician.setVerified(false);
        newTechnician.setActivated(true);
        newTechnician.setIdentificationType(technician.getIdentificationType());
        newTechnician.setIdentificationNumber(technician.getIdentificationNumber());
        return technicianRepository.save(newTechnician);
    }
    @Override
    public List<Technician> getAllTechnicians() {
        return technicianRepository.getAllTechnicians();
    }
}
