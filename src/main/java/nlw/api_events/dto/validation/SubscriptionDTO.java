package nlw.api_events.dto.validation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionDTO {

    private Integer eventId;
    private String eventName;
    private String eventTheme;
    private String emailSubscriber;

}
