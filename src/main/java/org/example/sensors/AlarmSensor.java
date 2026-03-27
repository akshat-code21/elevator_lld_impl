package org.example.sensors;

import org.example.buttons.AlarmButton;
import org.example.models.Elevator;

public class AlarmSensor {
    private final Elevator e;

    public AlarmSensor(Elevator e) {
        this.e = e;
    }

    public void alarmButtonPressed(){
        notifyElevator();
    }

    private void notifyElevator(){
        e.triggerAlarm();
    }
}
