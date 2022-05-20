package sit.ssi3.oasip.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
@Data
@Table(name = "event", indexes = {
        @Index(name = "fk_Event_EventCategory_idx", columnList = "eventCategoryID")
})
@Entity
public class Event {
    @Id
    @Column(name = "eventID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Name must not be null")
    @Size(min = 1, max = 100, message = "Name size must be between 1 and 100")
    @Column(name = "bookingName", nullable = false)
    private String bookingName;

    @Email(message = "Email must be well-formed email address", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    @NotEmpty(message = "Email must not be null")
    @Size(max = 100, message = "Email size must be between 1 and 100")
    @Column(name = "bookingEmail", nullable = false)
    private String bookingEmail;


    @NotNull(message = "Start time must not be null")
<<<<<<< HEAD
    @FutureOrPresent(message = "Start time must be a future date")
    @JsonFormat(timezone = "UTC")
=======
    @JsonFormat( timezone = "Asia/Bangkok")
    @Future(message = "Start time must be a future date")
>>>>>>> b32834345c973baea7deabe4ff5fc8dbd869ed90
    @Column(name = "eventStartTime", nullable = false)
    private Date eventStartTime;


    @Column(name = "eventDuration", nullable = false)
    private Integer eventDuration;


    @Size(max = 500, message = "size must be between 0 and 500")
    @Column(name = "eventNotes", length = 500)
    private String eventNotes;

    @NotNull(message = "Category ID must not be null")
    @ManyToOne(optional = false)
    @JoinColumn(name = "eventCategoryID", nullable = false)
    private Eventcategory eventCategoryID;

    @Transient
    @AssertFalse(message = "this event is overlapped with other events" )
    private boolean isOverlapped;



//    public Eventcategory getEventCategoryID() {
//        return eventCategoryID;
//    }
//
//    public void setEventCategoryID(Eventcategory eventCategoryID) {
//        this.eventCategoryID = eventCategoryID;
//    }
//
//    public String getEventNotes() {
//        return eventNotes;
//    }
//
//    public void setEventNotes(String eventNotes) {
//        this.eventNotes = eventNotes;
//    }
//
//    public Date getEventStartTime() {
//        return eventStartTime;
//    }
//
//    public void setEventDuration(Integer eventDuration) {
//        this.eventDuration = eventDuration;
//    }
//
//    public Integer getEventDuration() {
//        return eventDuration;
//    }
//
//    public void setEventStartTime(Date eventStartTime) {
//        this.eventStartTime = eventStartTime;
//    }
//
//    public String getBookingEmail() {
//        return bookingEmail;
//    }
//
//    public void setBookingEmail(String bookingEmail) {
//        this.bookingEmail = bookingEmail;
//    }
//
//    public String getBookingName() {
//        return bookingName;
//    }
//
//    public void setBookingName(String bookingName) {
//        this.bookingName = bookingName;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }


}

