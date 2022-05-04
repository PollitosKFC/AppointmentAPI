package pe.edu.upc.appointmentapi.EasyJobs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Appointment;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Customer;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Technician;
import pe.edu.upc.appointmentapi.EasyJobs.service.AppointmentService;

import java.util.List;

@Slf4j
@Controller
@RestController
@CrossOrigin(origins = "http://localhost:3306")
@RequestMapping(value ="/Appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/createAppointment")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment,
                                                         @RequestParam(name = "customerId") Long customerId,
                                                         @RequestParam(name = "technicianId") Long technicianId) {
        Appointment appointmentCreated = appointmentService.createAppointment(appointment, customerId, technicianId);
        return ResponseEntity.ok(appointmentCreated);
    }

    @PutMapping("/updateAppointment")
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment,
                                                         @RequestParam(name = "customerId",required = false) Long customerId,
                                                         @RequestParam(name = "technicianId",required = false) Long technicianId) {
        Appointment appointmentUpdated = appointmentService.updateAppointment(appointment, customerId, technicianId);
        return ResponseEntity.ok(appointmentUpdated);
    }

    @PutMapping("/deleteAppointment")
    public ResponseEntity<Appointment> deleteAppointment(@RequestBody Appointment appointment,
                                                         @RequestParam(name = "customerId",required = false) Long customerId,
                                                         @RequestParam(name = "technicianId",required = false) Long technicianId) {
        Appointment appointmentDeleted = appointmentService.deleteAppointment(appointment, customerId, technicianId);
        return ResponseEntity.ok(appointmentDeleted);
    }

    @GetMapping ("/listTechniciansByCustomerId")
    public ResponseEntity<List<Technician>> ListTechniciansByCustomerId(@RequestParam(name = "customerId",required = false) Long customerId) {
        List<Technician> technicians = appointmentService.findTechniciansByCustomerId(customerId);
        if (technicians.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(technicians);
    }

    @GetMapping ("/listTechniciansByCustomerIdAndStatus")
    public ResponseEntity<List<Technician>> ListTechniciansByCustomerIdAndStatus(@RequestParam(name = "customerId",required = false) Long customerId,
                                                                                 @RequestParam(name = "status",required = false) String status) {
        List<Technician> technicians = appointmentService.findTechniciansByCustomerIdAndStatus(customerId, status);
        if (technicians.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(technicians);
    }

    @GetMapping ("/listAppointmentsByCustomerId")
    public ResponseEntity<List<Appointment>> ListAppointmentsByCustomerId(@RequestParam(name = "customerId",required = false) Long customerId) {
        List<Appointment> appointments = appointmentService.findAppointmentsByCustomerId(customerId);
        if (appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(appointments);
    }

    @GetMapping ("/listCustomersByTechnicianId")
    public ResponseEntity<List<Customer>> ListCustomersByTechnicianId(@RequestParam(name = "technicianId",required = false) Long technicianId) {
        List<Customer> customers = appointmentService.findCustomersByTechnicianId(technicianId);
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping ("/listCustomersByTechnicianIdAndStatus")
    public ResponseEntity<List<Customer>> ListCustomersByTechnicianIdAndStatus(@RequestParam(name = "technicianId",required = false) Long technicianId,
                                                                               @RequestParam(name = "status",required = false) String status) {
        List<Customer> customers = appointmentService.findCustomersByTechnicianIdAndStatus(technicianId, status);
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping ("/listAppointmentsByTechnicianId")
    public ResponseEntity<List<Appointment>> ListAppointmentsByTechnicianId(@RequestParam(name = "technicianId",required = false) Long technicianId) {
        List<Appointment> appointments = appointmentService.findAppointmentsByTechnicianId(technicianId);
        if (appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(appointments);
    }

}
