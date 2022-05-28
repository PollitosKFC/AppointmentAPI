package pe.edu.upc.appointmentapi.EasyJobs.service;

import org.springframework.stereotype.Service;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Customer;

import java.util.List;

@Service
public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
}
