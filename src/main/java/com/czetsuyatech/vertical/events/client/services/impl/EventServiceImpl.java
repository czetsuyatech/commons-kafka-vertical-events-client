package com.czetsuyatech.vertical.events.client.services.impl;

import com.czetsuyatech.vertical.events.client.config.Constants;
import com.czetsuyatech.vertical.events.client.messaging.messages.EventType;
import com.czetsuyatech.vertical.events.client.messaging.messages.IamEvent;
import com.czetsuyatech.vertical.events.client.messaging.producers.IamEventProducer;
import com.czetsuyatech.vertical.events.client.services.EventService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  final IamEventProducer iamEventProducer;

  @Override
  public void generateEvent() {

    log.debug("Generating vertical event");

    iamEventProducer.publish(generateIamEvent());
  }

  private IamEvent generateIamEvent() {

    return IamEvent.builder()
        .eid(UUID.randomUUID().toString())
        .eventType(EventType.NEW_USER)
        .serviceName(Constants.SERVICE)
        .entityType(Constants.ENTITY_TYPE)
        .build();
  }
}
