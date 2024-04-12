package com.czetsuyatech.vertical.events.client.services;

import com.czetsuyatech.vertical.events.client.messaging.messages.EventType;
import com.czetsuyatech.vertical.events.client.services.impl.UserCreationServiceImpl;
import com.czetsuyatech.vertical.events.exceptions.UnsupportedEventException;
import com.czetsuyatech.vertical.events.messaging.messages.VerticalEventDTO;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class IamEventStrategy {

  private final UserCreationServiceImpl userCreationService;
  private Map<String, EventHandlerService> userEventHandlers;

  public void processEvent(VerticalEventDTO verticalEventDTO) {

    userEventHandlers = new HashMap<>() {{
      put(EventType.NEW_USER.name(), userCreationService);
    }};

    Optional.ofNullable(userEventHandlers.get(verticalEventDTO.getEvent().getEventType()))
        .orElseThrow(() -> new UnsupportedEventException())
        .processEvent(verticalEventDTO);
  }
}
