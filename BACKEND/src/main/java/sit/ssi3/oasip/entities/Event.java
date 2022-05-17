package sit.ssi3.oasip.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDateTime;
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

    @NotEmpty(message = "must not be empty")
    @Size(min = 1,max = 100,message = "size must be between 1 and 100")
    @Column(name = "bookingName", nullable = false)
    private String bookingName;

    @Email(message = "Email must be well-formed email address" , regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    @NotEmpty(message = "must not be empty")
    @Size(max = 255,message = "size must be between 1 and 255")
    @Column(name = "bookingEmail", nullable = false)
    private String bookingEmail;

    @Future(message = "must be a future date")
    @Column(name = "eventStartTime", nullable = false)
    private Date eventStartTime;

    @Column(name = "eventDuration", nullable = false)
    private Integer eventDuration;


    @Size(max = 500,message = "size must be between 0 and 500")
    @Column(name = "eventNotes", length = 500)
    private String eventNotes;

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

