package pe.edu.upc.appointmentapi.EasyJobs.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Appointment;

import java.util.List;

@Qualifier("appointmentRepository")
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query(value ="SELECT a FROM Appointment a WHERE a.technician.id = ?1 AND a.customer.id = ?2 AND a.name = ?3 AND a.delete = false")
    Appointment findByCustomerIdAndTechnicianIdAndName(Long customerId, Long technicianId, String Name);

    @Query(value ="SELECT a FROM Appointment a WHERE a.customer.id = ?1 AND a.technician.id = ?2 AND a.delete = false")
    List<Appointment> findByCustomerIdAndTechnician(Long customerId, Long technicianId);

    // list all the appointments of a technician
    @Query(value ="SELECT a FROM Appointment a WHERE a.technician.id = ?1 AND a.delete = false")
    List<Appointment> findAppointmentsByTechnicianId(Long id);

    // list all the appointments of a customer
    @Query(value ="SELECT a FROM Appointment a WHERE a.customer.id = ?1 AND a.delete = false")
    List<Appointment> findAppointmentsByCustomerId(Long id);
}
