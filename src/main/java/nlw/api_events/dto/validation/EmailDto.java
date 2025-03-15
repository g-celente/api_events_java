package nlw.api_events.dto.validation;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto {

    private Integer userId;
    private String emailTo;
    private String eventName;
    private String prettyName;
    private String subject;
    private String body;

}
