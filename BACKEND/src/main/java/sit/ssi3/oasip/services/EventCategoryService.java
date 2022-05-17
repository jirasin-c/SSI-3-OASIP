package sit.ssi3.oasip.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.ssi3.oasip.dtos.EventcategoryDTO;
import sit.ssi3.oasip.entities.Eventcategory;
import sit.ssi3.oasip.repositories.EventCategoryRepository;

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

    public EventcategoryDTO getEventCategoryByName(String eventCategoryName) {
        Eventcategory eventcategory = eventCategoryRepository.findByEventCategoryNameEquals(eventCategoryName);
        return modelMapper.map(eventcategory, EventcategoryDTO.class);

    }


    public Eventcategory getEventcategoryByID(Integer getCategoryByID) {
        Eventcategory eventcategory = eventCategoryRepository.findById(getCategoryByID).orElseThrow(() ->
                new RuntimeException(getCategoryByID + " Does not Exist !!!"));
        return modelMapper.map(eventcategory, Eventcategory.class);
    }

}
