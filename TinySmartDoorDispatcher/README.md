# Dispatcher

The dispatcher represents the business logic of the tiny smart door project.
it coordinates the broker topics and connects the other services to each other.

## DispatcherService

The Dispatcherservice uses the MQTT Gateway client to communicate with the broker. It handles all requests from all services and forwards them to the broker.

## Dispatcher

The Dispatcher provides the list of requests and their target destinations.