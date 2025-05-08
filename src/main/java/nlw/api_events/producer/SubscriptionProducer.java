package nlw.api_events.producer;

import nlw.api_events.dto.validation.EventDTO;
import nlw.api_events.dto.validation.SubscriptionDTO;
import nlw.api_events.model.Subscription;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionProducer {

    final RabbitTemplate rabbitTemplate;

    public SubscriptionProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "event")
    private String routingKey;

    public void sendEventSubscription(Subscription subscription) {
        var subscriptionDto =  new SubscriptionDTO();
        subscriptionDto.setEventId(subscription.getEvent().getEventId());
        subscriptionDto.setEventName(subscription.getEvent().getTitle());
        subscriptionDto.setEventTheme(subscription.getEvent().getTheme());
        subscriptionDto.setEmailSubscriber(subscription.getSubscriber().getEmail());

        rabbitTemplate.convertAndSend("", routingKey, subscriptionDto);
    }

}
