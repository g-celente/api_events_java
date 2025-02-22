package nlw.api_events.repository.event;

import nlw.api_events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event, Integer> {

    public Event findByPrettyName(String prettyName);
}
