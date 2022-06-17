package pe.edu.upc.appointmentapi.EasyJobs.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Appointment;
import pe.edu.upc.appointmentapi.EasyJobs.repository.AppointmentRepository;
import pe.edu.upc.appointmentapi.EasyJobs.repository.CustomerRepository;
import pe.edu.upc.appointmentapi.EasyJobs.repository.TechnicianRepository;
import pe.edu.upc.appointmentapi.EasyJobs.service.AppointmentService;
import pe.edu.upc.appointmentapi.EasyJobs.service.AppointmentServiceImpl;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AppointmentServiceImplTest {
    @MockBean
    private AppointmentRepository appointmentRepository;
    @MockBean
    private AppointmentService appointmentService;
    @MockBean
    private CustomerRepository customerRepository;
    @MockBean
    private TechnicianRepository technicianRepository;

    @TestConfiguration
        class AppointmentServiceImplTestConfiguration {
        @Bean
        public AppointmentService appointmentService(){return new AppointmentServiceImpl(customerRepository,technicianRepository,appointmentRepository);}
    }

    @Test
    @DisplayName("When Get Appointment By Appointment Id With Valid Id Then Returns Appointment")
    public void WhenGetAppointmentByAppointmentIdWithValidIdThenReturnsAppointment() {
        // Arrange
        Long id = 1L;
        Appointment appointment = new Appointment();
        appointment.setId(id);
        when(appointmentRepository.findById(id)).thenReturn(Optional.of(appointment));
        // Act
        Appointment appointmentResult = appointmentRepository.findById(id).get();
        // Assert
        assertThat(appointmentResult).isEqualTo(appointment);
    }

    @Test
    @DisplayName("When createdAppointment with valid data then return Appointment")
    public void WhenCreatedCustomerWithValidDataThenReturnCustomer() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setId(null);
        appointment.setName("name");
        appointment.setCreatedDate(null);
        appointment.setUpdateDate(null);
        appointment.setStatus("status");
        appointment.setStartDate(null);
        appointment.setDelete(false);
        long customerId = 1;
        long technicianId = 1;
        when(appointmentRepository.save(appointment)).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Appointment appointmentResult = appointmentService.createAppointment(appointment,customerId,technicianId);

        // Assert
        assertThat(appointmentResult).isEqualTo(appointment);
    }
    @Test
    @DisplayName("When UpdateAppointment with valid data then return Appointment")
    public void WhenUpdateAppointmentWithValidDataThenReturnAppointment() {
        // Arrange
        Long id = 1L;
        Appointment appointment = new Appointment();
        appointment.setId(id);


        Appointment appointmentUp = new Appointment();
        appointmentUp.setName("Name");
        appointmentUp.setCreatedDate(null);
        appointmentUp.setUpdateDate(null);
        appointmentUp.setStatus("status");

        Long customerId = 1L;
        Long technicianId = 1L;
        String name = "Appointment1";

        when(appointmentRepository.save(appointment)).thenAnswer(invocation -> invocation.getArgument(0));
        appointmentService.updateAppointment(appointmentUp, customerId, technicianId, name);

        // Act
        Appointment appointmentResult = appointmentRepository.getById(id);

        // Assert
        assertThat(appointmentResult).isEqualTo(appointmentRepository.getById(id));
    }
}
