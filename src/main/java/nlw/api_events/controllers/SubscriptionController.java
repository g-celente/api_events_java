package nlw.api_events.controllers;

import lombok.AllArgsConstructor;
import nlw.api_events.dto.ErrorMessage;
import nlw.api_events.dto.SubscriptionResponse;
import nlw.api_events.exception.NotFoundException;
import nlw.api_events.exception.SubscriptionExistingException;
import nlw.api_events.model.User;
import nlw.api_events.producer.UserProducer;
import nlw.api_events.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
@AllArgsConstructor
public class SubscriptionController {

    private final SubscriptionService service;

    @PostMapping({"/{prettyName}", "/{prettyName}/{userId}"})
    public ResponseEntity<?> newSubscription(@PathVariable String prettyName, @RequestBody User subscriber, @PathVariable(required = false) Integer userId) {
        try {
            SubscriptionResponse subscription = service.newSubscription(prettyName, subscriber, userId);

            if (subscription != null) {
                return ResponseEntity.ok(subscription);
            }

        } catch (NotFoundException e) {
            return ResponseEntity.status(404).body(new ErrorMessage(e.getMessage()));
        } catch (SubscriptionExistingException e) {
            return  ResponseEntity.status(409).body(new ErrorMessage(e.getMessage()));
        }

        return ResponseEntity.badRequest().build();
    }
}
