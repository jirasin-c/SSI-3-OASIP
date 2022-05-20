package sit.ssi3.oasip.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
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
    @Autowired
    private ListMapper listMapper;

//    public List<Eventcategory> getEventCategory(){
//        return eventCategoryRepository.findAll();
//    }

    public List<EventcategoryDTO> getEventCategory(String sortBy) {
        List<Eventcategory> eventCategoryList = eventCategoryRepository.findAll(Sort.by(sortBy).descending());
        return listMapper.mapList(eventCategoryList, EventcategoryDTO.class, modelMapper);
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
