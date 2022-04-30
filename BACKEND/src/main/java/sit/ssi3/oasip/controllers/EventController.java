package sit.ssi3.oasip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sit.ssi3.oasip.entities.Event;
import sit.ssi3.oasip.services.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {
    //Service layer
    @Autowired
    private EventService eventService;

    @GetMapping("")
    public List<Event> getEvent(@RequestParam(defaultValue = "eventStartTime") String sortBy){
        return eventService.getEvent(sortBy);
    }
}
