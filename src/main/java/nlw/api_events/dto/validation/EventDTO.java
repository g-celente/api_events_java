package nlw.api_events.dto.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import nlw.api_events.model.Event;

import java.time.LocalDate;

public record EventDTO (
        @NotBlank String title,
        @NotBlank String prettyName,
        @NotBlank String location,
        @NotBlank String theme,
        @NotNull Double price,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate
) {

    public Event mapEvent() {
        Event event = new Event();
        event.setTitle(this.title);
        event.setPrettyName(this.prettyName);
        event.setTheme(this.theme);
        event.setLocation(this.location);
        event.setPrice(this.price);
        event.setStartDate(this.startDate);
        event.setEndDate(this.endDate);
        return event;
    }

}

