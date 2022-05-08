package sit.ssi3.oasip.entities;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Table(name = "event", indexes = {
        @Index(name = "fk_Event_EventCategory_idx", columnList = "eventCategoryID")
})
@Entity
public class Event {
    @Id
    @Column(name = "eventID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "bookingName", nullable = false, length = 100)
    private String bookingName;

    @Column(name = "bookingEmail", nullable = false)
    private String bookingEmail;

    @Column(name = "eventStartTime", nullable = false)
    private Instant eventStartTime;

    @Column(name = "eventDuration", nullable = false)
    private Integer eventDuration;

    @Column(name = "eventNotes", length = 500)
    private String eventNotes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "eventCategoryID", nullable = false)
    private Eventcategory eventCategoryID;

    public Eventcategory getEventCategoryID() {
        return eventCategoryID;
    }

    public void setEventCategoryID(Eventcategory eventCategoryID) {
        this.eventCategoryID = eventCategoryID;
    }

    public String getEventNotes() {
        return eventNotes;
    }

    public void setEventNotes(String eventNotes) {
        this.eventNotes = eventNotes;
    }

    public Instant getEventStartTime() {
        return eventStartTime;
    }

    public void setEventDuration(Integer eventDuration){
        this.eventDuration = eventDuration;
    }
    public Integer getEventDuration(){
        return  eventDuration;
    }
    public void setEventStartTime(Instant eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getBookingEmail() {
        return bookingEmail;
    }

    public void setBookingEmail(String bookingEmail) {
        this.bookingEmail = bookingEmail;
    }

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

