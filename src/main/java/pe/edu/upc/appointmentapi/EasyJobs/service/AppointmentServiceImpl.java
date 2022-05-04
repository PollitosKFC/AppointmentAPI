package pe.edu.upc.appointmentapi.EasyJobs.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Appointment;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Customer;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Technician;
import pe.edu.upc.appointmentapi.EasyJobs.repository.AppointmentRepository;
import pe.edu.upc.appointmentapi.EasyJobs.repository.CustomerRepository;
import pe.edu.upc.appointmentapi.EasyJobs.repository.TechnicianRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl  implements AppointmentService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final TechnicianRepository technicianRepository;

    @Autowired
    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment createAppointment(Appointment appointment, Long customerId, Long technicianId) {
        appointment.setCustomerId(customerRepository.findById(customerId).orElse(null));
        appointment.setTechnicianId(technicianRepository.findById(technicianId).orElse(null));
        appointment.setCreatedDate(new Date());
        appointment.setDelete(false);
        appointment.setStatus("PENDING");
        return appointmentRepository.save(appointment);

    }

    @Override
    public Appointment updateAppointment(Appointment appointment, Long customerId, Long technicianId) {
        Appointment appointment1 = appointmentRepository.findByCutomerIdAndTechcinianId(customerId, technicianId);
        if (appointment1 == null) {
            return null;
        }
        appointment.setStatus(appointment.getStatus());
        appointment.setUpdateDate(appointment1.getUpdateDate());
        appointment.setStatus(appointment.getStatus());
        appointment.setStartDate(appointment1.getStartDate());
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment deleteAppointment(Appointment appointment, Long customerId, Long technicianId) {
        Appointment appointment1 = appointmentRepository.findByCutomerIdAndTechcinianId(customerId, technicianId);
        if (appointment1 == null) {
            return null;
        }
        appointment1.setDelete(true);
        return appointmentRepository.save(appointment1);
    }

    // Core method

    @Override
    public List<Technician> findTechniciansByCustomerId(Long id) {
        return customerRepository.findTechniciansByCustomerId(id);
    }

    @Override
    public List<Technician> findTechniciansByCustomerIdAndStatus(Long id, String status) {
        return customerRepository.findTechniciansByCustomerIdAndStatus(id, status);
    }

    @Override
    public List<Appointment> findAppointmentsByCustomerId(Long id) {
       return customerRepository.findAppointmentsByCustomerId(id);
    }

    @Override
    public List<Customer> findCustomersByTechnicianId(Long id) {
        return technicianRepository.findCustomersByTechnicianId(id);
    }

    @Override
    public List<Customer> findCustomersByTechnicianIdAndStatus(Long id, String status) {
        return technicianRepository.findCustomersByTechnicianIdAndStatus(id, status);
    }

    @Override
    public List<Appointment> findAppointmentsByTechnicianId(Long id) {
        return technicianRepository.findAppointmentsByTechnicianId(id);
    }

}
