package org.example.manager;

import org.example.models.Elevator;
import org.example.models.Floor;
import org.example.models.OutsideRequest;
import org.example.strategy.ElevatorCallStrategy;

import java.util.List;

public class ElevatorManager implements OutsideRequestListener{
    private final List<Elevator> elevators;
    private final List<Floor> floors;
    private ElevatorCallStrategy ecs;

    public ElevatorManager(List<Elevator> elevators, List<Floor> floors, ElevatorCallStrategy ecs) {
        this.elevators = elevators;
        this.floors = floors;
        this.ecs = ecs;
    }

    public void handleOutsideRequest(OutsideRequest or){
        Elevator selected = ecs.selectElevator(or, elevators);
        selected.outsideRequest(or);
    }

    public void addFloor(Floor f) {
        floors.add(f);
    }

    public void setEcs(ElevatorCallStrategy ecs) {
        this.ecs = ecs;
    }

    @Override
    public void onOutsideRequest(OutsideRequest or){
        handleOutsideRequest(or);
    }
}
