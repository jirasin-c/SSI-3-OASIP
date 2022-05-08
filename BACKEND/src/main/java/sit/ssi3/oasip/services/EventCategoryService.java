package sit.ssi3.oasip.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import sit.ssi3.oasip.entities.Eventcategory;
import sit.ssi3.oasip.repositories.EventCategoryRepository;

import java.util.List;

@Service
public class EventCategoryService {
    @Autowired
    private EventCategoryRepository eventCategoryRepository;
    public List<Eventcategory> getEventCategory(){
        return eventCategoryRepository.findAll();
    }
}
