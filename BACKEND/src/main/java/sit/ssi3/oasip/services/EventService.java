package sit.ssi3.oasip.services;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.ssi3.oasip.dtos.EventDTO;
import sit.ssi3.oasip.dtos.EventEditDTO;
import sit.ssi3.oasip.entities.Event;
import sit.ssi3.oasip.repositories.EventRepository;
import sit.ssi3.oasip.request.CreateEventRequest;
import sit.ssi3.oasip.utils.ListMapper;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;
    @Autowired
    private EventCategoryService eventCategoryService;
    @Autowired
    private static final Validator validator =
            Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .getValidator();

    public List<EventDTO> getEvent(String sortBy) {
        List<Event> eventList = eventRepository.findAll(Sort.by(sortBy).descending());
        return listMapper.mapList(eventList, EventDTO.class, modelMapper);
    }

    public EventDTO getEventById(Integer id) {
        Event event= eventRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event ID " + id + "Does not Exits"
                )
        );
        return modelMapper.map(event, EventDTO.class);
    }

    public Event createEvent(CreateEventRequest newEvent) {
        // map event dto request to event
        Event event = new Event();
        event.setId(null);
        event.setBookingName(newEvent.getBookingName());
        event.setEventDuration(newEvent.getEventDuration() == null ? newEvent.getEventCategoryID() == null
                ? null : eventCategoryService.getEventcategoryByID(newEvent.getEventCategoryID()).getEventDuration() : newEvent.getEventDuration());
        event.setEventNotes(newEvent.getEventNotes());
        event.setBookingEmail(newEvent.getBookingEmail());
        event.setEventCategoryID(newEvent.getEventCategoryID() == null ? null : eventCategoryService.getEventcategoryByID(newEvent.getEventCategoryID()));
        event.setEventStartTime(newEvent.getEventStartTime());
        event.setOverlapped(false);


        // find all event
        List<Event> eventList = this.eventRepository.findAll();
        if (newEvent.getEventStartTime() != null && event.getEventDuration() != null) {

            // check event overlapped
            eventList = eventList.stream().filter(oldEvent -> {
                Date startTime = oldEvent.getEventStartTime();
                Date newEventStartTime = event.getEventStartTime();
                Date endTime = new Date((startTime.getTime() + (oldEvent.getEventDuration() * 60000)));
                Date newEventEndTime = new Date((newEventStartTime.getTime() + (event.getEventDuration() * 60000)));
                if (newEventStartTime.compareTo(startTime) > 0 && newEventEndTime.compareTo(endTime) > 0 ||
                        newEventStartTime.compareTo(startTime) > 0 && newEventEndTime.compareTo(endTime) < 0 ||
                        newEventStartTime.compareTo(startTime) < 0 && newEventEndTime.compareTo(endTime) < 0 ||
                        newEventStartTime.compareTo(startTime) < 0 && newEventEndTime.compareTo(endTime) > 0) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
        }

        // check overlapped
        if (eventList.size() > 0 && newEvent.getEventStartTime() != null && event.getEventDuration() != null) {
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

    public EventDTO updateEvent(EventEditDTO updateEvent, Integer id) {

        Event newEvent = modelMapper.map(updateEvent, Event.class);
        Event event = eventRepository.findById(id).map(o -> mapEvent(o, newEvent))
                .orElseGet(() ->
                {
                    newEvent.setId(id);
                    return newEvent;
                });
        eventRepository.saveAndFlush(event);
        return modelMapper.map(event, EventDTO.class);
    }

    private Event mapEvent(Event existingEvent, Event updateEvent) {
        if (updateEvent.getEventNotes() != null)
            existingEvent.setEventNotes(updateEvent.getEventNotes());

        if (updateEvent.getEventStartTime() != null)
            existingEvent.setEventStartTime(updateEvent.getEventStartTime());
        return existingEvent;
    }


}
