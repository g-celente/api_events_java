package nlw.api_events.dto.validation;

import jakarta.validation.constraints.NotBlank;
import nlw.api_events.model.User;

public record UserDTO(
        @NotBlank String name,
        @NotBlank String email
) {

    public User mapUser () {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        return user;
    }

}
