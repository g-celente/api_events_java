package nlw.api_events.producer;

import lombok.AllArgsConstructor;
import nlw.api_events.dto.EmailDto;
import nlw.api_events.model.Subscription;
import nlw.api_events.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessage(Subscription subscription) {
        var emailDto = new EmailDto();
        emailDto.setUserId(subscription.getSubscriber().getId());
        emailDto.setEmailTo(subscription.getSubscriber().getEmail());
        emailDto.setEventName(subscription.getEvent().getTitle());
        emailDto.setPrettyName(subscription.getEvent().getPrettyName());
        emailDto.setSubject("Cadastro no Evento Realizado com Sucesso");
        emailDto.setBody(subscription.getSubscriber().getName()+ " seja Bem Vindo(a)!\nAgradecemos por se inscrever no evento "+ subscription.getEvent().getTitle() + "\nO evento será realizado no dia " + subscription.getEvent().getStartDate() + ". Esperamos você lá!");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
