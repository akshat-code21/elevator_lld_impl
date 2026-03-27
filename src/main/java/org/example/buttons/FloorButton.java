package org.example.buttons;

import org.example.models.Floor;

public class FloorButton extends Button {
    private final Floor floor;
    public FloorButton(Floor floor) {
        this.floor = floor;
    }
    public Floor getFloor() { return floor; }
}
