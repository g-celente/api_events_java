package nlw.api_events.service;

import lombok.AllArgsConstructor;
import nlw.api_events.model.Event;
import nlw.api_events.repository.event.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository repository;

    public List<Event> gettAllEvents() {
        try {
            List<Event> events = repository.findAll();
            return events;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Event store(Event event) {
        try {
            event.setPrettyName(event.getTitle().toLowerCase().replaceAll(" ", "-"));
            Event newEvent = repository.save(event);
            return newEvent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Event getEventByPrettyName(String prettyName) {
        try {
            Event event_pretty = repository.findByPrettyName(prettyName);
            return event_pretty;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
