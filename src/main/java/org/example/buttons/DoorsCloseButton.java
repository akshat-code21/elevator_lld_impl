package org.example.buttons;

import org.example.models.Door;

public class DoorsCloseButton extends Button {
    private final Door door;

    public DoorsCloseButton(Door door) {
        this.door = door;
    }

    @Override
    public void press() {
        super.press();
        door.open();
    }
}
