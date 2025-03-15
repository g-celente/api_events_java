package nlw.api_events.dto.common;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorMessage(int error, String message, List<ErrorCampo> errors) {

    public static ErrorMessage respostaPadrao(String message) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }

}
