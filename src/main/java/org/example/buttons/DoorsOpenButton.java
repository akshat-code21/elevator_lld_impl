package org.example.buttons;

import org.example.models.Door;

public class DoorsOpenButton extends Button {
    private final Door door;

    public DoorsOpenButton(Door door) {
        this.door = door;
    }

    @Override
    public void press() {
        super.press();
        door.open();
    }
}
