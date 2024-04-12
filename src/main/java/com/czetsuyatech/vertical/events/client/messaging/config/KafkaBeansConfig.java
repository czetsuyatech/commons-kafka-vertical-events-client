package com.czetsuyatech.vertical.events.client.messaging.config;

import com.czetsuyatech.vertical.events.client.messaging.messages.EventType;
import com.czetsuyatech.vertical.events.config.AbstractKafkaBeansConfig;
import com.czetsuyatech.vertical.events.messaging.messages.VerticalEventDTO;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

@Slf4j
@AllArgsConstructor
@Configuration
@EnableKafka
@ConditionalOnProperty(
    value = AbstractKafkaBeansConfig.BACKOFF_POLICY_ENABLED,
    havingValue = "true",
    matchIfMissing = true
)
public class KafkaBeansConfig extends AbstractKafkaBeansConfig {

  @NotNull
  public RecordFilterStrategy<Object, Object> getRejectRecordFilterStrategy() {

    return consumerRecord -> {
      log.debug("RecordFilterStrategy: {} ", consumerRecord.value());
      VerticalEventDTO verticalEvent = (VerticalEventDTO) consumerRecord.value();

      return Optional.ofNullable(verticalEvent.getEvent())
          .filter(ev -> EventType.isPresent(ev.getEventType()))
          .map(
              ev -> {
                log.info("Consuming bulk manual with eventId={}", ev.getEventId());
                VerticalEventDTO.Entity entity = verticalEvent.getEntity();
                return Optional.ofNullable(entity).isEmpty();
              })
          .orElse(Boolean.TRUE);
    };
  }

  @Override
  protected Logger getLogger() {
    return log;
  }
}
