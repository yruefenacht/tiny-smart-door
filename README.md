# Tiny Smart Door

This project demonstrates a possible implementation of a smart door, which can be opened and closed remotely. It uses tinkerforge devices and MQTT to communicate between them.

## Services

### App

The app presents the door on a GUI. It can also change the door state.

### MotorPoti

The MotorPoti is the microservice that manages the motorized linear poti bricklet.

### Dispatcher

The dispatcher is in the center of all microservices and coordinates them all.
Each request first gets sent here where it is handled by the business logic.
