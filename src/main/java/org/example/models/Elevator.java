package org.example.models;

import org.example.enums.ElevatorState;
import org.example.panels.InsideButtonPanel;

import java.util.List;

public class Elevator implements InsideRequestListener,WeightSensorListener,AlarmSensorListener{
    private final String id;
    private final InsideButtonPanel ibp;
    private ElevatorState es;
    private final Floor currentFloor;
    private double currentWeight;
    private final double maxWeight;
    private final List<Floor> insideRequests;
    private final List<OutsideRequest> outsideRequests;
    private final Door d;

    public Elevator(String id, InsideButtonPanel ibp, ElevatorState es, Floor currentFloor, double currentWeight, double maxWeight, List<Floor> insideRequests, List<OutsideRequest> outsideRequests, Door d) {
        this.id = id;
        this.ibp = ibp;
        this.es = es;
        this.currentFloor = currentFloor;
        this.currentWeight = currentWeight;
        this.maxWeight = maxWeight;
        this.insideRequests = insideRequests;
        this.outsideRequests = outsideRequests;
        this.d = d;
    }

    public void insideRequest(Floor f){
        insideRequests.add(f);
    }

    public void move(){
        if (es == ElevatorState.UNDER_MAINTENANCE || es == ElevatorState.ALARMED) return;
        if (es == ElevatorState.OVERLOADED) {
            triggerAlarm();
            return;
        }
        // process insideRequests and outsideRequests queues
        // update currentFloor
        // update ElevatorState to MOVING_UP / MOVING_DOWN / IDLE
    }

    public void setUnderMaintenance(){
        this.es = ElevatorState.UNDER_MAINTENANCE;
    }

    @Override
    public void updateWeight(double newWeight){
        this.currentWeight = newWeight;
        if (checkOverWeight()) {
            this.es = ElevatorState.OVERLOADED;
            this.d.open();
        }
    }

    private boolean checkOverWeight(){
        return this.currentWeight > maxWeight;
    }

    @Override
    public void triggerAlarm(){
        this.es = ElevatorState.ALARMED;
        System.out.println("ALARM TRIGGERED — Elevator stopped");
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void outsideRequest(OutsideRequest or) {
        outsideRequests.add(or);
    }

    @Override
    public void onInsideRequest(Floor f) {
        insideRequest(f);
    }
}
