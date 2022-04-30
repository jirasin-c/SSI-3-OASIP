package sit.ssi3.oasip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.ssi3.oasip.entities.Eventcategory;
import sit.ssi3.oasip.services.EventCategoryService;


import java.util.List;

@RestController
@RequestMapping("/api/event-category")
public class EventCategoryController {
    @Autowired
    private EventCategoryService eventCategoryService;

    @GetMapping("")
    private List<Eventcategory> getEventCategory(){
        return eventCategoryService.getEventCategory();
    }

}

