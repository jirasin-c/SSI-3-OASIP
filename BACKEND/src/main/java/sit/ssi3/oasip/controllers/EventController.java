package sit.ssi3.oasip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.ssi3.oasip.entities.Event;
import sit.ssi3.oasip.services.EventService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    //Service layer
    @Autowired
    private EventService eventService;

    @GetMapping("")
    public List<Event> getEvent(@RequestParam(defaultValue = "eventStartTime") String sortBy){
        return eventService.getEvent(sortBy);
    }
    @GetMapping("/{id}")
    public  Event getEventByID(@PathVariable Integer id){
        return eventService.getEventById(id);
    }

    @PostMapping("")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event newEvent){
        Event newEventSaved = eventService.createEvent(newEvent);
        return new ResponseEntity<Event>(newEventSaved,HttpStatus.CREATED);
    }

    @DeleteMapping("/{eventID}")
    public void cancelEvent(@PathVariable Integer eventID){
        eventService.cancelEvent(eventID);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@RequestBody Event updateEvent, @PathVariable Integer id){
        return eventService.updateEvent(updateEvent,id);

    }


}
