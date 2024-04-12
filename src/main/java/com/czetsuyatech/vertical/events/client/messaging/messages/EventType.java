package com.czetsuyatech.vertical.events.client.messaging.messages;

import java.util.Arrays;
import java.util.Locale;

public enum EventType {

  NEW_USER,
  NEW_LOGIN,
  STATUS_CHANGE,
  NAME_CHANGE,
  PASSWORD_CHANGE,
  EMAIL_CHANGE,
  PHONE_CHANGE,
  ROLE_CHANGE,
  GROUP_CHANGE;

  public static boolean isPresent(String value) {
    return Arrays.stream(EventType.values())
        .anyMatch(eventType -> eventType.name().equals(value.toUpperCase(Locale.ROOT)));
  }
}
