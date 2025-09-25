package com.czetsuyatech.vertical.events.client.services.impl;

import com.czetsuyatech.vertical.events.client.services.EventHandlerService;
import com.czetsuyatech.vertical.events.exceptions.EventFailedException;
import com.czetsuyatech.vertical.events.messaging.messages.VerticalEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FailedEventServiceImpl implements EventHandlerService {

  @Override
  public void processEvent(VerticalEventDTO event) {
    log.error("Failed event: {}", event);

    throw new EventFailedException("Invalid event");
  }
}
