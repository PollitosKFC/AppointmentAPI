package pe.edu.upc.appointmentapi.EasyJobs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "tbl_customer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Customer  extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, length = 40)
    private Long phoneNumber;

    @NotNull
    @Column(name = "firstName", length = 200)
    private String firstName;

    @NotNull
    @Column(name = "lastName", length = 200)
    private String lastName;

    @NotNull
    @Column(name = "address", length = 200)
    private String address;

    @NotNull
    @Column(name = "city", length = 200)
    private String city;

    @NotNull
    @Column(name = "district", length = 200)
    private String district;

    @NotNull
    @Column(name = "gender", length = 200)
    private String gender;

    @JsonIgnore
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> customer_appointment;
}
