package com.czetsuyatech.vertical.events.client.messaging.messages;

import java.util.Arrays;
import java.util.Locale;

public enum EventType {

  NEW_USER,
  FAILED_EVENT,
  RETRYABLE_EVENT;

  public static boolean isPresent(String value) {
    return Arrays.stream(EventType.values())
        .anyMatch(eventType -> eventType.name().equals(value.toUpperCase(Locale.ROOT)));
  }
}
