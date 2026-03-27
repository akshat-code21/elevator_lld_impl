package org.example.buttons;

import org.example.sensors.AlarmSensor;

public class AlarmButton extends Button {
    private final AlarmSensor as;

    public AlarmButton(AlarmSensor as) {
        this.as = as;
    }

    @Override
    public void press(){
        this.isPressed = true;
        as.alarmButtonPressed();
    }
}
