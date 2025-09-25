package com.czetsuyatech.vertical.events.client.services.impl;

import com.czetsuyatech.vertical.events.client.services.EventHandlerService;
import com.czetsuyatech.vertical.events.exceptions.EventRetryableException;
import com.czetsuyatech.vertical.events.messaging.messages.VerticalEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RetryableEventServiceImpl implements EventHandlerService {

  @Override
  public void processEvent(VerticalEventDTO event) {
    log.debug("Retryable event: {}", event);

    throw new EventRetryableException("Invalid event");
  }
}
