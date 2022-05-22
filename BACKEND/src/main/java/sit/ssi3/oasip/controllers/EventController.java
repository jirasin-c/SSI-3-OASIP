package sit.ssi3.oasip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.ssi3.oasip.dtos.EventDTO;
import sit.ssi3.oasip.dtos.EventEditDTO;
import sit.ssi3.oasip.entities.Event;
import sit.ssi3.oasip.request.CreateEventRequest;
import sit.ssi3.oasip.services.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    //Service layer
    @Autowired
    private EventService eventService;

    @GetMapping("")
    public List<EventDTO> getEvent(@RequestParam(defaultValue = "eventStartTime") String sortBy){
        return eventService.getEvent(sortBy);
    }
    @GetMapping("/{id}")
    public EventDTO getEventByID(@PathVariable Integer id){
        return eventService.getEventById(id);
    }

    @GetMapping("/filterCategory/{eventCatecoryId}")
    public List<EventDTO> getEventByCategoryId(@PathVariable Integer eventCatecoryId){
        return eventService.getEventByCategoryId(eventCatecoryId);
    }

    @GetMapping("/upComing")
    public  List<EventDTO> getEventUpComing(){
        return eventService.getEventUpComing();
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Event> createEvent(@RequestBody CreateEventRequest newEvent) {
        Event newEventSaved = eventService.createEvent(newEvent);
        return new ResponseEntity<Event>(newEventSaved, HttpStatus.OK);
    }

    @DeleteMapping("/{eventID}")
    public void cancelEvent(@PathVariable Integer eventID){
        eventService.cancelEvent(eventID);
    }

    @PutMapping("/{id}")
    public EventDTO updateEvent(@RequestBody EventEditDTO updateEvent, @PathVariable Integer id) {
        return eventService.updateEvent(updateEvent, id);

    }


}
