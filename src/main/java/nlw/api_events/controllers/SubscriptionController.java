package nlw.api_events.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nlw.api_events.dto.common.ErrorMessage;
import nlw.api_events.dto.common.SubscriptionResponse;
import nlw.api_events.dto.validation.UserDTO;
import nlw.api_events.exception.NotFoundException;
import nlw.api_events.exception.SubscriptionExistingException;
import nlw.api_events.service.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscription")
@AllArgsConstructor
public class SubscriptionController {

    private final SubscriptionService service;

    @PostMapping({"/{prettyName}", "/{prettyName}/{userId}"})
    public ResponseEntity<?> newSubscription(@PathVariable String prettyName, @RequestBody @Valid UserDTO subscriber, @PathVariable(required = false) Integer userId) {
        try {
            SubscriptionResponse subscription = service.newSubscription(prettyName, subscriber.mapUser(), userId);

            if (subscription != null) {
                return ResponseEntity.ok(subscription);
            }

            return ResponseEntity.badRequest().build();
        } catch (SubscriptionExistingException e) {
            return  ResponseEntity.status(409).body(new ErrorMessage(HttpStatus.CONFLICT.value(), e.getMessage(), List.of()));
        }
    }
}
