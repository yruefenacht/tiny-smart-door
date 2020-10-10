# Tiny Smart Door App

The smart door app consists of the following components:

## Components

```
app
│   README.md 
│
└───model
│   │   Door.java
│   │   DoorStatusUpdateCallback.java
│   
└───service
│   │   DoorService.java
│
└───ui
│   └───door
│       │   DoorViewModel.java
│       
```

### Door

Captures the state of the door.

### DoorService

Uses the GateWayClient to publish changes of the door.

### DoorViewModel

Displays the door status with switch button.