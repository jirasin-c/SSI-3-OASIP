package sit.ssi3.oasip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.ssi3.oasip.dtos.EventDTO;
import sit.ssi3.oasip.dtos.EventcategoryDTO;
import sit.ssi3.oasip.entities.Eventcategory;
import sit.ssi3.oasip.services.EventCategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/event-category")
public class EventCategoryController {
    @Autowired
    private EventCategoryService eventCategoryService;

//    @GetMapping("")
//    private List<Eventcategory> getEventCategory() {
//        return eventCategoryService.getEventCategory();
//    }

    @GetMapping("")
    public List<EventcategoryDTO> getEventCategory(@RequestParam(defaultValue = "id") String sortBy){
        return eventCategoryService.getEventCategory(sortBy);
    }

    @GetMapping("/getCategoryName")
    private EventcategoryDTO getEventCategoryByName(@RequestParam String eventCategoryName) {
        return eventCategoryService.getEventCategoryByName(eventCategoryName);
    }


    @GetMapping("/{getCategoryByID}")
    private Eventcategory getEventcategoryByID(@PathVariable Integer getCategoryByID) {
        return eventCategoryService.getEventcategoryByID(getCategoryByID);
    }

}

