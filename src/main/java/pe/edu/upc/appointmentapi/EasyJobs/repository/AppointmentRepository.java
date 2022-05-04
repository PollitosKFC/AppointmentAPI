package pe.edu.upc.appointmentapi.EasyJobs.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Appointment;

@Qualifier("appointmentRepository")
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findByCutomerIdAndTechcinianId(Long customerId, Long technicianId);
}
