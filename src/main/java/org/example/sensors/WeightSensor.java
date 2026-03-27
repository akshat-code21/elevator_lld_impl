package org.example.sensors;

import org.example.models.Elevator;

public class WeightSensor {
    private double currWeight;
    private final Elevator e;

    public WeightSensor(Elevator e) {
        this.e = e;
    }

    public void setCurrWeight(int newWeight){
        this.currWeight = newWeight;
        notifyElevator();
    }
    public void notifyElevator(){
        e.updateWeight(currWeight);
    }
}
