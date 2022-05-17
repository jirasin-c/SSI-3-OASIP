package sit.ssi3.oasip.request;

import lombok.Data;
import sit.ssi3.oasip.entities.Eventcategory;

import java.util.Date;

@Data
public class CreateEventRequest {
    private Integer id;
    private String bookingName;
    private String bookingEmail;
    private Date eventStartTime;
    private Integer eventDuration;
    private String eventNotes;
    private Eventcategory eventCategoryID;

}
