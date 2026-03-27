package org.example.buttons;

import org.example.sensors.AlarmSensor;

public class AlarmButton extends Button {
    private boolean isPressed;
    private AlarmSensor as;

    @Override
    public void press(){
        isPressed = true;
        as.alarmButtonPressed();
    }

    public boolean isPressed() {
        return isPressed;
    }
}
