import paho.mqtt.client as mqtt
import MotorPoti as mp

class MotorPotiService:

    DOOR_TOPIC = "door/+/"
    ACTION_INTENT = "Intent"
    ACTION_EVENT = "Event"
    ACTION_STATUS = "Status"
    ACTION_ANY = "+"

    def __init__(self, host, port, username, password):
        motorPoti = mp.MotorPoti()
        client = mqtt.Client()
        client.on_connect = self.on_connect
        client.on_message = self.on_message
        client.username_pw_set(username, password)
        client.connect(host, port)
        client.loop_forever()


    def on_connect(self, client, userdata, flags, rc):
        if rc == 0:
            print("Connected")
            client.subscribe(DOOR_TOPIC + ACTION_ANY)
        else:
            print("Connection failed: ", rc)


    def on_door_updated(message):
        client.publish(DOOR_TOPIC + ACTION_EVENT, message)
        

    def on_message(self, client, userdata, message):
        command = str(message.payload.decode("utf-8"))
        topic = message.topic
        print("message received: ", command)
        print("message topic: ", topic)

        if command == "open":
            motorPoti.open_door(on_door_updated)
        elif command == "close":
            motorPoti.close_door(on_door_updated)
        else:
            print("Could not interpret message")
