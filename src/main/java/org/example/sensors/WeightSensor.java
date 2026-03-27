package org.example.sensors;

import org.example.models.Elevator;
import org.example.models.WeightSensorListener;

public class WeightSensor {
    private double currWeight;
    private final WeightSensorListener wsl;

    public WeightSensor(Elevator wsl) {
        this.wsl = wsl;
    }

    public void setCurrWeight(double newWeight){
        this.currWeight = newWeight;
        notifyElevator();
    }
    public void notifyElevator(){
        wsl.updateWeight(currWeight);
    }
}
