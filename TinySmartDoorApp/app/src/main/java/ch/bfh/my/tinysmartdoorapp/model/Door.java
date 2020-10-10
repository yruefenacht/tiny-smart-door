package ch.bfh.my.tinysmartdoorapp.model;

public class Door {

    private static final String DOOR_OPEN_MQTT = "open";
    private static final String DOOR_CLOSED_MQTT = "close";
    private static final String DOOR_OPEN_GUI = "Open";
    private static final String DOOR_CLOSED_GUI = "Closed";
    private boolean status;
    private DoorStatusUpdateCallBack callBack;

    public void toggleDoor() {
        this.setStatus(!this.status);
    }

    public void setStatus(boolean status) {
        this.status = status;
        this.callBack.updateDoorStatus(status);
    }

    public boolean getStatus() {
        return this.status;
    }

    public static String getStatusMqtt(boolean status) {
        return status ? DOOR_OPEN_MQTT : DOOR_CLOSED_MQTT;
    }

    public static String getStatusText(boolean status) {
        return status ? DOOR_OPEN_GUI : DOOR_CLOSED_GUI;
    }

    public void setStatusUpdatedCallback(DoorStatusUpdateCallBack callback) {
        this.callBack = callback;
    }
}
