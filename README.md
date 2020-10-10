# Tiny Smart Door

This project demonstrates a possible implementation of a smart door, which can be opened and closed remotely. It uses tinkerforge devices and MQTT to communicate between them.

## System
![System Overview](https://github.com/yruefenacht/tiny-smart-door/blob/master/thumbnail/smart-door-system.png)
_Illustration by [Mac Müller](https://github.com/macivo)_

The system consists of the following components:
* App
* Server (MQTT-Broker)
* [FoG Computer](https://en.wikipedia.org/wiki/Fog_computing)
* Sensors, Master Brick

## Dataflow
![Dataflow](https://github.com/yruefenacht/tiny-smart-door/blob/master/thumbnail/smart-door-dataflow.png)
_Illustration by [Mac Müller](https://github.com/macivo)_

The software architecture follows the [MQTT-Gateway pattern](https://github.com/knr1/ch.quantasy.iot.mqtt.gateway.tutorial).

## Sensors

The list of utilised sensors is as follows:
- 1x [Master Brick](https://www.tinkerforge.com/de/doc/Hardware/Bricks/Master_Brick.html#master-brick) 
- 2x [Distance US Bricklet 2.0](https://www.tinkerforge.com/de/doc/Hardware/Bricklets/Distance_US_V2.html)
- 2x [Motorized Linear Poti Bricklet](https://www.tinkerforge.com/de/doc/Hardware/Bricklets/Motorized_Linear_Poti.html)

## Services

### App

The app presents the door on a GUI. It can also change the door state.

### MotorPoti

The MotorPoti is the microservice that manages the motorized linear poti bricklet.

### Dispatcher

The dispatcher is in the center of all microservices and coordinates them all.
Each request first gets sent here where it is handled by the business logic.

## App
Demo image:  

<img src="https://github.com/yruefenacht/tiny-smart-door/blob/master/thumbnail/smart-door-app.png" width="300">
