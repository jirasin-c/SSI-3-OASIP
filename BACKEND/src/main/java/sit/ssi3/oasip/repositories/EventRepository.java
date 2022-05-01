package sit.ssi3.oasip.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.ssi3.oasip.entities.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {
}