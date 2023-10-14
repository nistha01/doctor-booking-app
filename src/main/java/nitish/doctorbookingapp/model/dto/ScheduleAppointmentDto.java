package nitish.doctorbookingapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nitish.doctorbookingapp.model.Appointment;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleAppointmentDto {
    AuthenticationInputDto authenticationInput;
    Appointment appointment;
}
