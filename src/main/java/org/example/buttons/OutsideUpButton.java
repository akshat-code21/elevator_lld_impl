package org.example.buttons;

import org.example.enums.Direction;
import org.example.manager.ElevatorManager;
import org.example.models.Floor;
import org.example.models.OutsideRequest;

public class OutsideUpButton extends Button {
    private final Floor f;
    private final ElevatorManager em;

    public OutsideUpButton(Floor f, ElevatorManager em) {
        this.f = f;
        this.em = em;
    }

    @Override
    public void press() {
        super.press();
        em.onOutsideRequest(new OutsideRequest(f, Direction.UP));
    }
}
