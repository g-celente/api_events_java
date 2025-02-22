package nlw.api_events.repository.subscription;

import nlw.api_events.model.Event;
import nlw.api_events.model.Subscription;
import nlw.api_events.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    public Subscription findByEventAndSubscriber(Event event, User user);

}

