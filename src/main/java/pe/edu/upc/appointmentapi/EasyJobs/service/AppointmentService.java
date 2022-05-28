package pe.edu.upc.appointmentapi.EasyJobs.service;

import org.springframework.stereotype.Service;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Appointment;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Customer;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Technician;

import java.util.List;

@Service
public interface AppointmentService {
    Appointment createAppointment(Appointment appointment, Long customerId, Long technicianId);
    Appointment updateAppointment(Appointment appointment, Long customerId, Long technicianId, String name);
    Appointment deleteAppointment(Appointment appointment, Long customerId, Long technicianId, String name);
    List<Technician> findTechniciansByCustomerId(Long id);
    List<Technician> findTechniciansByCustomerIdAndStatus(Long id, String status);
    List<Appointment> findAppointmentsByCustomerId(Long id);
    List<Customer> findCustomersByTechnicianId(Long id);
    List<Customer> findCustomersByTechnicianIdAndStatus(Long id, String status);
    List<Appointment> findAppointmentsByTechnicianId(Long id);
    List<Appointment> findByCustomerIdAndTechnician(Long customerId, Long technicianId);

}
