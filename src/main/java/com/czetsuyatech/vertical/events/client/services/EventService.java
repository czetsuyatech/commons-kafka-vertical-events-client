package com.czetsuyatech.vertical.events.client.services;

public interface EventService {

  void generateEvent();

  void generateFailedEvent();

  void generateRetryableEvent();
}
