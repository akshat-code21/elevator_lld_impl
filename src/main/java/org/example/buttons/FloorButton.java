package org.example.buttons;

import org.example.models.Floor;
import org.example.models.InsideRequestListener;

public class FloorButton extends Button {
    private final Floor floor;
    private final InsideRequestListener irl;
    public FloorButton(Floor floor, InsideRequestListener irl) {
        this.floor = floor;
        this.irl = irl;
    }
    public Floor getFloor() { return floor; }
    @Override
    public void press() {
        super.press();
        irl.onInsideRequest(floor);
    }
}
