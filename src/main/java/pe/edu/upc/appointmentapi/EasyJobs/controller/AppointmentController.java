package pe.edu.upc.appointmentapi.EasyJobs.controller;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Appointment;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Customer;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Technician;
import pe.edu.upc.appointmentapi.EasyJobs.resource.*;
import pe.edu.upc.appointmentapi.EasyJobs.service.AppointmentService;


import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RestController
@CrossOrigin(origins = "http://localhost:3306")
@RequestMapping(value ="/Appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/createAppointmentBy/{technicianId}And/{customerId}")
    public AppointmentResource createAppointment(@RequestBody SaveAppointmentResource appointment,
                                                         @RequestParam(name = "customerId") Long customerId,
                                                         @RequestParam(name = "technicianId") Long technicianId) {
        Appointment appointmentCreated = appointmentService.createAppointment(convertToEntity(appointment), customerId, technicianId);
        return convertToResource(appointmentCreated);
    }

    @PutMapping("/updateAppointmentBy/{technicianId}And/{customerId}And/{name}")
    public AppointmentResource updateAppointment(@RequestBody SaveAppointmentResource appointment,
                                                         @RequestParam(name = "customerId") Long customerId,
                                                         @RequestParam(name = "technicianId") Long technicianId,
                                                 @RequestParam(name = "name") String name) {
        Appointment appointmentUpdated = appointmentService.updateAppointment(convertToEntity(appointment), customerId, technicianId,name);
        if (appointmentUpdated == null) {
            return null;
        }
        return convertToResource(appointmentUpdated);
    }

    @DeleteMapping("/deleteAppointmentBy/{technicianId}And/{customerId}And/{name}")
    public AppointmentResource deleteAppointment(@RequestBody SaveAppointmentResource appointment,
                                                         @RequestParam(name = "customerId") Long customerId,
                                                         @RequestParam(name = "technicianId") Long technicianId,
                                                         @RequestParam(name = "name") String name) {
        Appointment appointmentDeleted = appointmentService.deleteAppointment(convertToEntity(appointment), customerId, technicianId,name);
        if (appointmentDeleted == null) {
            return null;
        }
        return convertToResource(appointmentDeleted);
    }

    @GetMapping ("/findTechniciansBy/{customerId}")
    public List<TechnicianResource> ListTechniciansByCustomerId(@RequestParam(name = "customerId") Long customerId) {
        List<Technician> technicians = appointmentService.findTechniciansByCustomerId(customerId);
        if (technicians == null) {
            return null;
        }
        List<TechnicianResource> technicianResourceList = technicians.stream().map(technician -> {
            return convertToResourceTechnician(technician);
        }).collect(Collectors.toList());
        return technicianResourceList;
    }

    @GetMapping ("/listTechniciansByAnd/{customerId}And/{status}")
    public List<TechnicianResource> ListTechniciansByCustomerIdAndStatus(@RequestParam(name = "customerId") Long customerId,
                                                                                 @RequestParam(name = "status") String status) {
        List<Technician> technicians = appointmentService.findTechniciansByCustomerIdAndStatus(customerId, status);
        if (technicians == null) {
            return null;
        }
        List<TechnicianResource> technicianResourceList = technicians.stream().map(technician -> {
            return convertToResourceTechnician(technician);
        }).collect(Collectors.toList());
        return technicianResourceList;
    }

    @GetMapping ("/findAppointmentsByCustomerId/{customerId}")
    public List<AppointmentResource> ListAppointmentsByCustomerId(@PathVariable(name = "customerId",required = false) Long customerId) {
        List<Appointment> appointments = appointmentService.findAppointmentsByCustomerId(customerId);
        if (appointments == null) {
            return null;
        }
        List<AppointmentResource> appointmentResourceList = appointments.stream().map(appointment -> {
            return convertToResource(appointment);
        }).collect(Collectors.toList());
        return appointmentResourceList;
    }

    @GetMapping ("/findCustomersBy/{technicianId}")
    public List<CustomerResource> ListCustomersByTechnicianId(@RequestParam(name = "technicianId") Long technicianId) {
        List<Customer> customers = appointmentService.findCustomersByTechnicianId(technicianId);
        if (customers == null) {
            return null;
        }
        List<CustomerResource> customerResourceList = customers.stream().map(customer -> {
            return convertToResourceCustomer(customer);
        }).collect(Collectors.toList());
        return customerResourceList;
    }

    @GetMapping ("/findCustomersBy/{technicianId}And/{status}")
    public List<CustomerResource> ListCustomersByTechnicianIdAndStatus(@RequestParam(name = "technicianId") Long technicianId,
                                                                               @RequestParam(name = "status") String status) {
        List<Customer> customers = appointmentService.findCustomersByTechnicianIdAndStatus(technicianId, status);
        if (customers == null) {
            return null;
        }
        List<CustomerResource> customerResourceList = customers.stream().map(customer -> {
            return convertToResourceCustomer(customer);
        }).collect(Collectors.toList());
        return customerResourceList;
    }

    @GetMapping ("/findAppointmentsByTechnicianId/{technicianId}")
    public List<AppointmentResource> ListAppointmentsByTechnicianId(@PathVariable(name = "technicianId") Long technicianId) {
        List<Appointment> appointments = appointmentService.findAppointmentsByTechnicianId(technicianId);
        if (appointments == null) {
            return null;
        }
        List<AppointmentResource> appointmentResourceList = appointments.stream().map(appointment -> {
            return convertToResource(appointment);
        }).collect(Collectors.toList());
        return appointmentResourceList;
    }
    @GetMapping ("/findAppointmentsByCustomerIdAndTechnicianId/{customerId}And/{technicianId}")
    public  List<AppointmentResource> ListAppointmentsByCustomerIdAndTechnicianId(@PathVariable(name = "customerId") Long customerId,
                                                 @PathVariable(name = "technicianId") Long technicianId){
        List<Appointment> appointments = appointmentService.findByCustomerIdAndTechnician(customerId,technicianId);
        if (appointments == null) {
            return null;
        }
        List<AppointmentResource> appointmentResourceList = appointments.stream().map(appointment -> {
            return convertToResource(appointment);
        }).collect(Collectors.toList());
        return appointmentResourceList;
    }

    private Appointment convertToEntity(SaveAppointmentResource resource){
        return modelMapper.map(resource, Appointment.class);
    }
    private AppointmentResource convertToResource(Appointment entity) {
        return modelMapper.map(entity, AppointmentResource.class);
    }
    private TechnicianResource convertToResourceTechnician(Technician entity) {
        return modelMapper.map(entity, TechnicianResource.class);
    }
    private CustomerResource convertToResourceCustomer(Customer entity) {
        return modelMapper.map(entity, CustomerResource.class);
    }
}
