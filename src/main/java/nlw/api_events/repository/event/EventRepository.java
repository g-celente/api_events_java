package nlw.api_events.repository.event;

import nlw.api_events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Integer> {
    public Event findByPrettyName(String prettyName);

    @Query("SELECT e FROM Event e WHERE e.theme IN :themes")
    public List<Event> findByThemes(List<String> themes);
}
