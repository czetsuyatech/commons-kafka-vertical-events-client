package com.czetsuyatech.vertical.events.client.messaging.consumers;

import com.czetsuyatech.vertical.events.client.services.IamEventStrategy;
import com.czetsuyatech.vertical.events.messaging.messages.VerticalEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(value = "unified.kafka.vertical-events.enabled", havingValue = "true")
@Service
@Slf4j
@RequiredArgsConstructor
public class IamEventsConsumer {

  private static final String CONSUMER_TOPIC = "${unified.kafka.vertical-events.topics.iam-vertical}";
  private static final String CONSUMER_GROUP = "${unified.kafka.vertical-events.consumers.iam-group}";
  private final IamEventStrategy iamEventStrategy;

  @KafkaListener(
      topics = CONSUMER_TOPIC,
      groupId = CONSUMER_GROUP,
      containerFactory = "concurrentKafkaListenerContainerFactory")
  public void handleEvent(VerticalEventDTO verticalEventDTO) {

    log.debug("Receives event with type={}", verticalEventDTO.getEvent().getEventType());

    iamEventStrategy.processEvent(verticalEventDTO);
  }

  @DltHandler
  void handle(String in, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
    log.debug("{} from {}", in, topic);
  }
}

