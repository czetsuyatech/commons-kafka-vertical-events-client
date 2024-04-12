package com.czetsuyatech.vertical.events.client.services.impl;

import com.czetsuyatech.vertical.events.client.services.EventHandlerService;
import com.czetsuyatech.vertical.events.messaging.messages.VerticalEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserCreationServiceImpl implements EventHandlerService {

  @Override
  public void processEvent(VerticalEventDTO event) {

    log.debug("Processing user creation event={}", event.getEntity());
  }
}
