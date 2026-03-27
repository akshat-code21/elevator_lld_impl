package org.example.sensors;

import org.example.buttons.AlarmButton;
import org.example.models.Elevator;

public class AlarmSensor {
    private AlarmButton ab;
    private Elevator e;

    public void alarmButtonPressed(){
        if(this.ab.isPressed()){
            notifyElevator();
        }
    }

    private void notifyElevator(){
        e.playSong();
    }
}
