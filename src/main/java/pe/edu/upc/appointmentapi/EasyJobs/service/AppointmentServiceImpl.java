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
        Appointment newAppointment = new Appointment();
        if (customerRepository.existsById(customerId) && technicianRepository.existsById(technicianId)) {
            newAppointment.setCustomer(customerRepository.findById(customerId).orElse(null));
            newAppointment.setTechnician(technicianRepository.findById(technicianId).orElse(null));
            newAppointment.setName(appointment.getName());
            newAppointment.setCreatedDate(new Date());
            newAppointment.setUpdateDate(new Date());
            newAppointment.setStartDate(new Date());
            newAppointment.setDelete(false);
            newAppointment.setStatus(appointment.getStatus());
            return appointmentRepository.save(newAppointment);
        }
       return null;
    }

    @Override
    public Appointment updateAppointment(Appointment appointment, Long customerId, Long technicianId, String name) {
        Appointment appointment1 = appointmentRepository.findByCustomerIdAndTechnicianIdAndName(customerId, technicianId,name);
        if (appointment1 == null) {
            return null;
        }
        appointment1.setName(appointment.getName());
        appointment1.setStatus(appointment.getStatus());
        appointment1.setUpdateDate(new Date());
        appointment1.setStartDate(new Date());
        return appointmentRepository.save(appointment1);
    }

    @Override
    public Appointment deleteAppointment(Appointment appointment, Long customerId, Long technicianId, String name) {
        Appointment appointment1 = appointmentRepository.findByCustomerIdAndTechnicianIdAndName(customerId, technicianId, name);
        if (appointment1 == null) {
            return null;
        }
        appointment1.setDelete(true);
        appointment1.setUpdateDate(new Date());
        appointment1.setStatus(appointment.getStatus());
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
       return appointmentRepository.findAppointmentsByCustomerId(id);
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
        return appointmentRepository.findAppointmentsByTechnicianId(id);
    }

    @Override
    public List<Appointment> findByCustomerIdAndTechnician(Long customerId, Long technicianId) {
        return appointmentRepository.findByCustomerIdAndTechnician(customerId, technicianId);
    }
}
