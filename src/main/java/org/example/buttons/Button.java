package org.example.buttons;

public abstract class Button {
    protected boolean isPressed;
    public void press(){
        this.isPressed = true;
    }
}
