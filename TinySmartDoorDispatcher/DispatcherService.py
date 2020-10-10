import paho.mqtt.client as mqtt
import DispatcherConfig

class DispatcherService:

    def __init__(self, host, port, username, password):
        client = mqtt.Client()
        client.on_connect = self.on_connect
        client.on_message = self.on_message
        client.username_pw_set(username, password)
        client.connect(host, port)
        client.loop_forever()

    def subscribe():
        client.subscribe(SUBSCRIBE_TO_APP)
        client.subscribe(SUBSCRIBE_TO_DOOR)
        client.subscribe(SUBSCRIBE_TO_DISTANCE)

    def on_connect(self, client, userdata, flags, rc):
        if rc == 0:
            print("Connected")
            self.subscribe()
        else:
            print("Connection failed: ", rc)

    def on_message(self, client, userdata, message):
        command = str(message.payload.decode("utf-8"))
        topic = message.topic
        print("message received: ", command)
        print("message topic: ", topic)

        topic_fragments = topic.split('/')
        topic_device = topic_fragments[0]
        topic_device_id = topic_fragments[1]
        topic_action = topic_fragments[2]

        if topic_device == DEVICE_APP and topic_action == ACTION_EVENT:
            client.publish(PUBLISH_TO_DOOR + ACTION_INTENT, command)
