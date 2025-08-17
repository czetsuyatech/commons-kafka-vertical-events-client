package com.czetsuyatech.vertical.events.client.web.controllers;

import com.czetsuyatech.vertical.events.client.services.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
public class VerticalEventsController {

  final EventService eventService;

  @PostMapping("/events/action/generate-event")
  public void generateEvent() {

    log.debug("Receive request to generate an event");

    eventService.generateEvent();
  }

  @PostMapping("/events/action/generate-failed-event")
  public void generateFailedEvent() {

    log.debug("Receive request to generate an failed event");

    eventService.generateFailedEvent();
  }

  @PostMapping("/events/action/generate-retryable-event")
  public void generateRetryableEvent() {

    log.debug("Receive request to generate an retryable event");

    eventService.generateRetryableEvent();
  }
}
