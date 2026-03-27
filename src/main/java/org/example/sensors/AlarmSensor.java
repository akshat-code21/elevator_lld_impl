package org.example.sensors;

import org.example.models.AlarmSensorListener;

public class AlarmSensor {
    private final AlarmSensorListener asl;

    public AlarmSensor(AlarmSensorListener asl) {
        this.asl = asl;
    }

    public void alarmButtonPressed(){
        notifyElevator();
    }

    private void notifyElevator(){
        asl.triggerAlarm();
    }
}
