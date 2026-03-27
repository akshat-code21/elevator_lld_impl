package org.example.models;

import org.example.enums.ElevatorState;
import org.example.panels.InsideButtonPanel;

import java.util.List;

public class Elevator {
    private String id;
    private InsideButtonPanel ibp;
    private ElevatorState es;
    private Floor currentFloor;
    private double currentWeight;
    private double maxWeight;
    private List<Floor> insideRequests;
    private List<OutsideRequest> outsideRequests;
    private Door d;

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

    }

    public void setUnderMaintenance(){
        this.es = ElevatorState.UNDER_MAINTENANCE;
    }

    public void updateWeight(double newWeight){
        this.currentWeight = newWeight;
    }

    private boolean checkOverWeight(){
        return this.currentWeight > maxWeight;
    }

    public void playSong(){
        System.out.println("Playing songs");
    }

}
