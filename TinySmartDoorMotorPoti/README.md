# MotorPoti

This microservice represents the motorized linear poti sensor. It consists of the following components:

## MotorPotiService

The MotorPotiService uses the GateWayClient to communicate with the MQTT-Broker and then uses the MotorPoti class to apply changes to the sensors.

## MotorPoti

This class is directly connected to the motorized linear poti.
It is able to apply new values onto the motorized linear poti.
