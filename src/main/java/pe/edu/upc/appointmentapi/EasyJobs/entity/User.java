package pe.edu.upc.appointmentapi.EasyJobs.entity;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class User{

    @NotNull
    @Column(name = "userName",length = 200)
    private String userName;

    @NotNull
    @Column(name = "password", length = 250)
    private String password;

    @NotNull
    @Column(name = "email", length = 250)
    private String email;

    @NotNull
    @Column(name = "type", length = 50)
    private String type;

    @NotNull
    @Column(name = "identificationType", length = 200)
    private String identificationType;

    @NotNull
    @Column(name = "identificationNumber")
    private int identificationNumber;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registerDate")
    private Date registerDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedDate")
    private Date updatedDate;

    @NotNull
    @Column(name = "activated")
    private Boolean activated;

}