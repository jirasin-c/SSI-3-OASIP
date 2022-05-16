package sit.ssi3.oasip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sit.ssi3.oasip.entities.Event;
import sit.ssi3.oasip.repositories.EventCategoryRepository;
import sit.ssi3.oasip.repositories.EventRepository;
import sit.ssi3.request.CreateEventRequest;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
//    @Autowired
//    private ValidationHandler validationHandler;

//    @Autowired
//    private EventCategoryRepository eventCategoryRepository;

    public List<Event> getEvent(String sortBy) {
        return eventRepository.findAll(Sort.by(sortBy).descending());
    }

    public Event getEventById(Integer id) {
        return eventRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event ID " + id + "Does not Exits"
                )
        );
    }

    @AssertTrue(message = "overlapped")
    public boolean createEvent(Event newEvent) {
//        return eventRepository.saveAndFlush(newEvent)

        List<Event> eventList = this.eventRepository.findAll();
        eventList = eventList.stream().filter(event -> {
            Date startTime = event.getEventStartTime();
            Date newEventStartTime = newEvent.getEventStartTime();
            Date endTime = new Date((startTime.getTime() + (event.getEventDuration() * 60000)));
            Date newEventEndTime = new Date((newEventStartTime.getTime() + (newEvent.getEventDuration() * 60000)));
            if (newEventStartTime.compareTo(startTime) > 0 && newEventEndTime.compareTo(endTime) > 0) {
                return true;
            }
            if (newEventStartTime.compareTo(startTime) > 0 && newEventEndTime.compareTo(endTime) < 0) {
                return true;
            }
            if (newEventStartTime.compareTo(startTime) < 0 && newEventEndTime.compareTo(endTime) < 0) {
                return true;
            }

            if (newEventStartTime.compareTo(startTime) < 0 && newEventEndTime.compareTo(endTime) > 0) {
                return true;
            }
            return false;

        }).collect(Collectors.toList());
        newEvent.setOverlapped(false);
        if (eventList.size() > 0) {
//            newEvent.setOverlapped(true);
        }
    this.validateEvent(newEvent);
//            this.eventRepository.saveAndFlush(newEvent);
//        this.eventRepository.saveAndFlush(newEvent);
        return false;
    }

    private Event validateEvent(@Valid Event newEvent) {
        return this.eventRepository.saveAndFlush(newEvent);
    }


    public void cancelEvent(Integer eventID) {
        eventRepository.findById(eventID).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Event ID " + eventID + " Does Not Exits!!!"));
        eventRepository.deleteById(eventID);
    }

    public Event updateEvent(Event updateEvent, Integer id) {
        Event event = eventRepository.findById(id).map(o -> mapEvent(o, updateEvent))
                .orElseGet(() ->
                {
                    updateEvent.setId(id);
                    return updateEvent;
                });
        return eventRepository.saveAndFlush(event);
    }

    private Event mapEvent(Event existingEvent, Event updateEvent) {
        existingEvent.setEventNotes(updateEvent.getEventNotes());
        existingEvent.setEventStartTime(updateEvent.getEventStartTime());
        return existingEvent;
    }


}
