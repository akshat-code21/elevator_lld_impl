package org.example.buttons;

import org.example.enums.Direction;
import org.example.manager.ElevatorManager;
import org.example.manager.OutsideRequestListener;
import org.example.models.Floor;
import org.example.models.OutsideRequest;

public class OutsideDownButton extends Button {
    private final Floor f;
    private final OutsideRequestListener orl;

    public OutsideDownButton(Floor f, ElevatorManager orl) {
        this.f = f;
        this.orl = orl;
    }

    @Override
    public void press() {
        super.press();
        orl.onOutsideRequest(new OutsideRequest(f, Direction.DOWN));
    }
}
