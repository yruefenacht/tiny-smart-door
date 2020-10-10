package ch.bfh.my.tinysmartdoorapp.service;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

import ch.bfh.my.tinysmartdoorapp.model.Credentials;
import ch.bfh.my.tinysmartdoorapp.model.Door;

import static android.content.ContentValues.TAG;

public class DoorService {

    private Door door;
    private MqttAndroidClient gateWayClient;
    private PropertiesService propertiesService;
    private final String CLIENT_ID = MqttClient.generateClientId();
    private final String TOPIC_PUBLISH = "app/"+this.CLIENT_ID +"/Event";
    private final String TOPIC_SUBSCRIBE = "app/"+this.CLIENT_ID+"/Intent";

    public DoorService(Context context) {
        this.door = new Door();
        this.propertiesService = new PropertiesService(context);
        this.gateWayClient = new MqttAndroidClient(context, this.propertiesService.getURL(), this.CLIENT_ID);
        this.connect();
    }

    private void connect() {
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            Credentials credentials = this.propertiesService.getCredentials();
            options.setUserName(credentials.getUsername());
            options.setPassword(credentials.getPassword().toCharArray());
            IMqttToken token = this.gateWayClient.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "Connected");
                    DoorService.this.subscribe();
                }
                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d(TAG, "Connection failed");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void subscribe() {
        int qos = 0;
        try {
            this.gateWayClient.subscribe(TOPIC_SUBSCRIBE, qos);
            this.gateWayClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {

                }
                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    Log.d(TAG, "Message arrived");
                    String msg = new String(message.getPayload());
                    if(msg.equals("open")) {
                        DoorService.this.getDoor().setStatus(true);
                    }
                    else if(msg.equals("close")) {
                        DoorService.this.getDoor().setStatus(false);
                    }
                    else {
                        Log.d(TAG, "Could not interpret message");
                    }
                }
                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void toggleDoor() {
        byte[] encodedPayload;
        try {
            this.door.toggleDoor();
            encodedPayload = this.door.getStatusMqtt(this.door.getStatus()).getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            this.gateWayClient.publish(this.TOPIC_PUBLISH, message);
        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }
    }

    public Door getDoor() {
        return this.door;
    }
}
