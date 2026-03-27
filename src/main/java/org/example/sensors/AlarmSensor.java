package org.example.sensors;

import org.example.models.AlarmSensorListener;
import org.example.models.Elevator;

public class AlarmSensor {
    private final AlarmSensorListener asl;

    public AlarmSensor(Elevator asl) {
        this.asl = asl;
    }

    public void alarmButtonPressed(){
        notifyElevator();
    }

    private void notifyElevator(){
        asl.triggerAlarm();
    }
}
