# Czetsuya Tech | Commons/Unified Kafka Vertical Events Client

This project is a client demonstration of the messaging library (commons-kafka-vertical-events) using vertical events.

## How to Run

1. Run the docker-compose file under the folder docker. This will start a zookeeper and kafka
   instance.

```docker-compose up```

2. Run the project in your favorite IDE.

3. Open a Kafka IDE like BigData Kafka in IntelliJ or Conduktor. Create a consumer for the topic:
   HmDataOps-Iam-vertical-events. So that you can watch the events get created and consume.

3. Open Postman and send a POST http request to the
   endpoint: http://localhost:8080/api/events/actions/generate. This will create an event send and
   consume using the library.

## Repositories

- https://github.com/czetsuyatech/commons-kafka-vertical-events
