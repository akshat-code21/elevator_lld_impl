package org.example.manager;

import org.example.models.Elevator;
import org.example.models.Floor;
import org.example.models.OutsideRequest;
import org.example.strategy.ElevatorCallStrategy;

import java.util.List;

public class ElevatorManager {
    private List<Elevator> elevators;
    private List<Floor> floors;
    private ElevatorCallStrategy ecs;

    public ElevatorManager(List<Elevator> elevators, List<Floor> floors, ElevatorCallStrategy ecs) {
        this.elevators = elevators;
        this.floors = floors;
        this.ecs = ecs;
    }

    public void handleOutsideRequest(OutsideRequest or){

    }

    public void setEcs(ElevatorCallStrategy ecs) {
        this.ecs = ecs;
    }
}
