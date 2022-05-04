package pe.edu.upc.appointmentapi.EasyJobs.entity;

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

    @Column(unique = true, length = 40)
    private Long phoneNumber_n;

    @NotNull
    @Column(unique = true, length = 200)
    private String firstName_n;

    @NotNull
    @Column(unique = true, length = 200)
    private String lastName_n;

    @Column(unique = true, length = 200)
    private String address_n;

    @NotNull
    @Column(unique = true, length = 200)
    private String city_n;

    @NotNull
    @Column(unique = true, length = 200)
    private String district_n;

    @NotNull
    @Column(unique = true, length = 200)
    private String gender_n;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> customer_appointment;
}
