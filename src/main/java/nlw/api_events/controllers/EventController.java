package nlw.api_events.controllers;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nlw.api_events.dto.validation.EventDTO;
import nlw.api_events.model.Event;
import nlw.api_events.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService service;

    @GetMapping
    public ResponseEntity<List<Event>> index() {
        try {
            List<Event> events = service.gettAllEvents();
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<Event> store(@RequestBody @Valid EventDTO eventDTO) {
        try {
            Event newEvent = service.store(eventDTO.mapEvent());
            return ResponseEntity.ok(newEvent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{prettyName}")
    public ResponseEntity<Event> getEventByPrettyName(@PathVariable("prettyName") String prettyName) {
        try {
            Event event_pretty = service.getEventByPrettyName(prettyName);

            if (event_pretty != null) {
                return ResponseEntity.ok().body(event_pretty);
            }

            return  ResponseEntity.notFound().build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().body("Evento deletado com Sucesso");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
