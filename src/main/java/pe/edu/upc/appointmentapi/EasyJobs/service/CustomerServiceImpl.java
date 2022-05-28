package pe.edu.upc.appointmentapi.EasyJobs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.appointmentapi.EasyJobs.entity.Customer;
import pe.edu.upc.appointmentapi.EasyJobs.repository.CustomerRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        Customer newCustomer = new Customer();
        newCustomer.setUserName(customer.getUserName());
        newCustomer.setRegisterDate(new Date());
        newCustomer.setUpdatedDate(new Date());
        newCustomer.setFirstName(customer.getFirstName());
        newCustomer.setLastName(customer.getLastName());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setPassword(customer.getPassword());
        newCustomer.setPhoneNumber(customer.getPhoneNumber());
        newCustomer.setAddress(customer.getAddress());
        newCustomer.setCity(customer.getCity());
        newCustomer.setDistrict(customer.getDistrict());
        newCustomer.setGender(customer.getGender());
        newCustomer.setType("CUSTOMER");
        newCustomer.setActivated(true);
        return customerRepository.save(newCustomer);
    }
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAllCustomer();
    }
}
