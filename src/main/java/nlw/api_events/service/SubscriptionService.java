package nlw.api_events.service;

import lombok.AllArgsConstructor;
import nlw.api_events.dto.SubscriptionResponse;
import nlw.api_events.exception.NotFoundException;
import nlw.api_events.exception.SubscriptionExistingException;
import nlw.api_events.model.Event;
import nlw.api_events.model.Subscription;
import nlw.api_events.model.User;
import nlw.api_events.repository.event.EventRepository;
import nlw.api_events.repository.subscription.SubscriptionRepository;
import nlw.api_events.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public SubscriptionResponse newSubscription(String event_name, User user, Integer userId) {

        Event event = eventRepository.findByPrettyName(event_name);

        if (event == null) {
            throw new NotFoundException("Evento "+event_name+ " não existe");
        }

        User userRec = userRepository.findByEmail(user.getEmail());

        if (userRec == null) {
            userRec = userRepository.save(user);
        }

        Subscription subscription = new Subscription();
        subscription.setEvent(event);
        subscription.setSubscriber(userRec);

        if (userId != null) {
            User indication = userRepository.findById(userId).orElse(null);

            if (indication == null) {
                throw new NotFoundException("O usuário com id "+userId+ "não foi encontrado");
            }

            subscription.setIndication(indication);
        }

        Subscription response = subscriptionRepository.findByEventAndSubscriber(event, userRec);

        if (response != null) {
            throw new SubscriptionExistingException("O usuário: "+userRec.getName()+" já está cadastrado no evento "+event.getTitle());
        }

        Subscription res = subscriptionRepository.save(subscription);
        return  new SubscriptionResponse(res.getId(), "http://codecraft.com/"+res.getEvent().getPrettyName()+"/"+res.getSubscriber().getId());
    }

}
