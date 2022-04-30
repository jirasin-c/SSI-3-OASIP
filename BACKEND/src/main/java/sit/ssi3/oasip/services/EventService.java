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

    public List<Event> getEvent(String sortBy){
        Sort sort = Sort.by(sortBy);
        return eventRepository.findAll(sort);
    }
}
