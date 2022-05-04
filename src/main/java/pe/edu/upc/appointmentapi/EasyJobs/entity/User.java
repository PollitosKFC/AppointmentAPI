package pe.edu.upc.appointmentapi.EasyJobs.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true, length = 200)
    private String userName;

    @NotNull
    @Column(unique = true, length = 250)
    private String password;

    @NotNull
    @Column(unique = true, length = 250)
    private String email;

    @NotNull
    @Column(unique = true, length = 50)
    private String type;

    @NotNull
    @Column(unique = true, length = 200)
    private String identificationType;

    @NotNull
    @Column()
    private int identificationNumber;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(unique = true)
    private Date registerDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(unique = true)
    private Date updatedDate;

}
