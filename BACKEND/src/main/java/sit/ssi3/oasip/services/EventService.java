package sit.ssi3.oasip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.ssi3.oasip.entities.Event;
import sit.ssi3.oasip.repositories.EventRepository;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

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

    public Event createEvent(Event newEvent) {
        return eventRepository.saveAndFlush(newEvent);
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
