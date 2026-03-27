package org.example.models;

import org.example.enums.DoorStatus;

public class Door {
    private DoorStatus doorStatus;

    public Door(DoorStatus doorStatus) {
        this.doorStatus = doorStatus;
    }

    public void open(){
        this.doorStatus = DoorStatus.DOOR_OPEN;
    }

    public void close(){
        this.doorStatus = DoorStatus.DOOR_CLOSE;
    }
}
