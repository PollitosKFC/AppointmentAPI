package pe.edu.upc.appointmentapi.EasyJobs.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Customer;
import pe.edu.upc.appointmentapi.EasyJobs.resource.CustomerResource;
import pe.edu.upc.appointmentapi.EasyJobs.resource.SaveCustomerResource;
import pe.edu.upc.appointmentapi.EasyJobs.service.CustomerService;


import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@CrossOrigin(origins = "http://localhost:3306")
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/createCustomer")
    public CustomerResource createCustomer(@RequestBody SaveCustomerResource customer){
        Customer customerCreated = customerService.createCustomer(convertToEntity(customer));
        return convertToResource(customerCreated);
    }

    @GetMapping(value = "/getAllCustomers")
    public List<CustomerResource> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        if (customers == null) {
            return null;
        }
        List<CustomerResource> customerResourceList = customers.stream().map(customer -> {
            return convertToResource(customer);
        }).collect(Collectors.toList());
        return customerResourceList;
    }
    private Customer convertToEntity(SaveCustomerResource resource){
        return modelMapper.map(resource, Customer.class);
    }
    private CustomerResource convertToResource(Customer entity) {
        return modelMapper.map(entity, CustomerResource.class);
    }
}
