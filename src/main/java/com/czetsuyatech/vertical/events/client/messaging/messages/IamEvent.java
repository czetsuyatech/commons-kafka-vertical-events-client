package com.czetsuyatech.vertical.events.client.messaging.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamEvent {

  private EventType eventType;
  private String serviceName;
  private String entityType;
  private String eid;
  private String scope;
}
