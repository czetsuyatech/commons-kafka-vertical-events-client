package com.czetsuyatech.vertical.events.client.messaging.producers;

import com.czetsuyatech.vertical.events.client.messaging.messages.IamEvent;
import com.czetsuyatech.vertical.events.config.KafkaConfig;
import com.czetsuyatech.vertical.events.messaging.messages.KeyAttributes;
import com.czetsuyatech.vertical.events.messaging.messages.VerticalEventDTO;
import com.czetsuyatech.vertical.events.messaging.messages.VerticalEventDTO.Entity;
import com.czetsuyatech.vertical.events.messaging.messages.VerticalEventDTO.Event;
import com.czetsuyatech.vertical.events.messaging.producers.AbstractKafkaProducer;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@AllArgsConstructor
@Slf4j
public class IamEventProducer extends AbstractKafkaProducer<IamEvent, VerticalEventDTO> {

  private final KafkaConfig kafkaConfig;
  private final KafkaTemplate<String, VerticalEventDTO> kafkaTemplate;

  @Override
  protected CompletableFuture<SendResult<String, VerticalEventDTO>> publishMessage(String topic, VerticalEventDTO event) {
    return kafkaTemplate.send(topic, event.getEvent().getEventId(), event);
  }

  @Override
  protected void publishFailure(VerticalEventDTO message, Throwable throwable) {
    log.error("Message: {} failed to be sent to kafka", message, throwable);
  }

  @Override
  protected void publishSuccess(VerticalEventDTO message, SendResult<String, VerticalEventDTO> result) {

    log.debug(
        "Message: {} was sent to kafka with offset: {}",
        message,
        result.getRecordMetadata().offset());
  }

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  @Override
  public void publish(IamEvent event) {

    log.debug("Sending to topic={}, event={}", kafkaConfig.getTopics().getIamVertical(), event);

    sendMessage(
        kafkaConfig.getTopics().getIamVertical(),
        getVerticalEvent(event));
  }

  private VerticalEventDTO getVerticalEvent(IamEvent event) {

    return VerticalEventDTO.builder()
        .event(Event.builder()
            .timestamp(OffsetDateTime.now().toString())
            .service(event.getServiceName())
            .eventType(event.getEventType().name())
            .build())
        .entity(Entity.builder()
            .type(event.getEntityType())
            .keyAttributes(Map.of(
                KeyAttributes.EID, event.getEid()))
            .build())
        .build();
  }
}
