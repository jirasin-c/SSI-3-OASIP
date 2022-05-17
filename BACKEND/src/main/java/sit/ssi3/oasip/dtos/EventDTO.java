package sit.ssi3.oasip.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
public class EventDTO implements Serializable {
    @NotEmpty(message = "must not be empty")
    @Size(min = 1, max = 100, message = "size must be between 1 and 100")
    private String bookingName;
    @Email(message = "Email must be well-formed email address", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    @NotEmpty(message = "must not be empty")
    @Size(max = 255, message = "size must be between 1 and 255")
    private  String bookingEmail;
    @Future(message = "must be a future date")
    private  Date eventStartTime;
    private  Integer eventDuration;
    @Size(max = 500, message = "size must be between 0 and 500")
    private  String eventNotes;
    private  String eventCategoryName;

}
