package org.example.buttons;

import org.example.enums.Direction;
import org.example.models.OutsideRequestListener;
import org.example.models.Floor;
import org.example.models.OutsideRequest;

public class OutsideUpButton extends Button {
    private final Floor f;
    private final OutsideRequestListener orl;

    public OutsideUpButton(Floor f, OutsideRequestListener orl) {
        this.f = f;
        this.orl = orl;
    }

    @Override
    public void press() {
        super.press();
        orl.onOutsideRequest(new OutsideRequest(f, Direction.UP));
    }
}
