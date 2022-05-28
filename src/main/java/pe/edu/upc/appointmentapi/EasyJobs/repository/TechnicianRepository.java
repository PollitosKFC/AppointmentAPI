package pe.edu.upc.appointmentapi.EasyJobs.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Appointment;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Customer;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Technician;

import java.util.List;
@Qualifier("technicianRepository")
@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {

    // find all the customers of a specific technician
   // @Query(value ="SELECT c FROM Customer c JOIN Appointment a WHERE a.technician.id = ?1 AND a.customer.id = c.id")
    //List<Customer> findCustomersByTechnicianId(Long id);
     @Query(value ="SELECT c FROM Customer c JOIN fetch c.customer_appointment a WHERE a.technician.id = ?1 AND a.customer.id = c.id AND a.delete = false")
    List<Customer> findCustomersByTechnicianId(Long id);

    // find all clients of a technician based on appointment status
    @Query(value ="SELECT c FROM Customer c JOIN fetch c.customer_appointment a WHERE a.technician.id = ?1 AND a.customer.id = c.id AND a.status = ?2 AND a.delete = false")
    List<Customer> findCustomersByTechnicianIdAndStatus(Long id, String status);

    // list all technicians
    @Query(value ="SELECT t FROM Technician t WHERE t.activated = true")
    List<Technician> getAllTechnicians();
}
