package pe.edu.upc.appointmentapi.EasyJobs.resource;

import lombok.Data;

@Data
public class TechnicianResource {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String identificationType;
    private Long identificationNumber;
    private Long phoneNumber;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String district;
    private Boolean verified;
    private String gender;
    private Boolean activated;
}
