# Tiny Smart Door

The Tiny Smart Door project represents an automated door.
The door is opened by sensors and can also be accessed with an app.

[Private Cloud](https://bernerfachhochschule-my.sharepoint.com/:f:/g/personal/mullk8_bfh_ch/EjBKoVGfGOpMhRxXZtkxHgQBEisxrykcIBvCzPZZv-Go-g)

## Services

### App

The app presents the door on a GUI. It can also change the door state.

### MotorPoti

The MotorPoti is the microservice that manages the motorized linear poti bricklet.

### Dispatcher

The dispatcher is in the center of all microservices and coordinates them all.
Each request first gets sent here where it is handled by the business logic.
