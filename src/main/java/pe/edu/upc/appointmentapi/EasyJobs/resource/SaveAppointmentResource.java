package pe.edu.upc.appointmentapi.EasyJobs.resource;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class SaveAppointmentResource {
    @NotBlank
    @NotNull
    @Size(max = 250)
    private String name;

    @NotBlank
    @NotNull
    @Size(max = 50)
    private String status;
}
