package sit.ssi3.oasip.services;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.ssi3.oasip.entities.Event;

import sit.ssi3.oasip.repositories.EventRepository;
import sit.ssi3.oasip.request.CreateEventRequest;

import javax.validation.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private static final Validator validator =
            Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .getValidator();

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


    public Event createEvent(CreateEventRequest newEvent) {
        // find all event
        List<Event> eventList = this.eventRepository.findAll();
        // check event overlapped
        eventList = eventList.stream().filter(event -> {
            Date startTime = event.getEventStartTime();
            Date newEventStartTime = newEvent.getEventStartTime();
            Date endTime = new Date((startTime.getTime() + (event.getEventDuration() * 60000)));
            Date newEventEndTime = new Date((newEventStartTime.getTime() + (newEvent.getEventDuration() * 60000)));
            if (newEventStartTime.compareTo(startTime) > 0 && newEventEndTime.compareTo(endTime) > 0 ||
                    newEventStartTime.compareTo(startTime) > 0 && newEventEndTime.compareTo(endTime) < 0 ||
                    newEventStartTime.compareTo(startTime) < 0 && newEventEndTime.compareTo(endTime) < 0 ||
                    newEventStartTime.compareTo(startTime) < 0 && newEventEndTime.compareTo(endTime) > 0) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        // map event dto request to event
        Event event = new Event();
        event.setId(null);
        event.setBookingName(newEvent.getBookingName());
        event.setEventDuration(newEvent.getEventDuration());
        event.setEventNotes(newEvent.getEventNotes());
        event.setBookingEmail(newEvent.getBookingEmail());
        event.setEventCategoryID(newEvent.getEventCategoryID());
        event.setEventStartTime(newEvent.getEventStartTime());
        event.setOverlapped(false);
        // check overlapped
        if (eventList.size() > 0) {
            event.setOverlapped(true);
        }
        // validate event field
        Set<ConstraintViolation<Event>> violations = validator.validate(event);
        for (ConstraintViolation<Event> violation : violations) {
            System.out.println(violation.getMessage());
        }
        // return when error message contains
        if (violations.size() > 0) throw new ConstraintViolationException(violations);
        // custom error response
        return this.eventRepository.saveAndFlush(event); // return success service
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
