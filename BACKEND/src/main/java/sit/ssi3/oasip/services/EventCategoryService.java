package sit.ssi3.oasip.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;
import sit.ssi3.oasip.dtos.EventDTO;
import sit.ssi3.oasip.dtos.EventcategoryDTO;
import sit.ssi3.oasip.entities.Event;
import sit.ssi3.oasip.entities.Eventcategory;
import sit.ssi3.oasip.repositories.EventCategoryRepository;
import sit.ssi3.oasip.utils.ListMapper;

import java.util.List;

@Service
public class EventCategoryService {
    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Eventcategory> getEventCategory(){
        return eventCategoryRepository.findAll();
    }

        public EventcategoryDTO getEventCategoryByName(String eventCategoryName){
        Eventcategory eventcategory = eventCategoryRepository.findByEventCategoryNameEquals(eventCategoryName);
                return modelMapper.map(eventcategory, EventcategoryDTO.class);

        }




}
