package org.example.models;

import org.example.enums.Direction;

public class OutsideRequest {
    private Floor f;
    private Direction d;

    public OutsideRequest(Floor f, Direction d) {
        this.f = f;
        this.d = d;
    }
}
