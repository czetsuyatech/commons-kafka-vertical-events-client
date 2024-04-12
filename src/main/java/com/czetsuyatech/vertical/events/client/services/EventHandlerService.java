package com.czetsuyatech.vertical.events.client.services;

import com.czetsuyatech.vertical.events.messaging.messages.VerticalEventDTO;

public interface EventHandlerService {

  void processEvent(VerticalEventDTO event);
}
