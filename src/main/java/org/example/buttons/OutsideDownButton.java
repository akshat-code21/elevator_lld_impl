package org.example.buttons;

import org.example.enums.Direction;
import org.example.models.OutsideRequestListener;
import org.example.models.Floor;
import org.example.models.OutsideRequest;

public class OutsideDownButton extends Button {
    private Floor f;
    private final OutsideRequestListener orl;

    public OutsideDownButton(Floor f, OutsideRequestListener orl) {
        this.f = f;
        this.orl = orl;
    }

    public void setFloor(Floor f) {
        this.f = f;
    }

    @Override
    public void press() {
        super.press();
        orl.onOutsideRequest(new OutsideRequest(f, Direction.DOWN));
    }
}
