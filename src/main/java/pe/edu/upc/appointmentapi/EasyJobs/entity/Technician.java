package pe.edu.upc.appointmentapi.EasyJobs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "tbl_technician")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Technician extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, length = 40)
    private Long phoneNumber;

    @NotNull
    @Column(unique = true, length = 200)
    private String firstName;

    @NotNull
    @Column(unique = true, length = 200)
    private String lastName;

    @NotNull
    @Column(unique = true, length = 200)
    private String address;

    @NotNull
    @Column(unique = true, length = 200)
    private String city;

    @NotNull
    @Column(unique = true, length = 200)
    private String district;

    @NotNull
    @Column(unique = true)
    private Boolean verified;

    @NotNull
    @Column(unique = true, length = 200)
    private String gender;

    @OneToMany(mappedBy = "technician",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> technician_appointment;

}
