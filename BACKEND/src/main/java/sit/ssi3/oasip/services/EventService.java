package sit.ssi3.oasip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;
import sit.ssi3.oasip.entities.Event;
import sit.ssi3.oasip.repositories.EventRepository;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEvent(String sortBy){
        return eventRepository.findAll(Sort.by(sortBy).descending());
    }
    public Event getEventById(Integer id){
        return eventRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event ID " + id + "Does not Exits"
                )
        );
    }
}
