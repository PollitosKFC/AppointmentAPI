package pe.edu.upc.appointmentapi.EasyJobs.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Appointment;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Customer;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Technician;

import java.util.List;
@Qualifier("customerRepository")
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // find all the technician of a specific customer
    @Query(value ="SELECT t FROM Technician t JOIN fetch t.technician_appointment a WHERE a.customer.id =?1 AND a.technician.id = t.id AND a.delete = false")
    List<Technician> findTechniciansByCustomerId(Long id);

    // find all technician of a customer based on appointment status
    @Query(value ="SELECT t FROM Technician t JOIN fetch t.technician_appointment a WHERE a.customer.id = ?1 AND a.technician.id = t.id AND a.status = ?2 AND a.delete = false")
    List<Technician> findTechniciansByCustomerIdAndStatus(Long id, String status);

    // list all customers
    @Query(value ="SELECT c FROM Customer c WHERE c.activated = true")
    List<Customer> findAllCustomer();
}
