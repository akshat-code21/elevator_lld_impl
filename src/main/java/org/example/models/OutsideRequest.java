package org.example.models;

import org.example.enums.Direction;

public class OutsideRequest {
    private Floor f;
    private Direction d;

    public OutsideRequest(Floor f, Direction d) {
        this.f = f;
        this.d = d;
    }

    public Floor getFloor() {
        return f;
    }

    public void setFloor(Floor f) {
        this.f = f;
    }

    public Direction getDoor() {
        return d;
    }

    public void setDoor(Direction d) {
        this.d = d;
    }
}
