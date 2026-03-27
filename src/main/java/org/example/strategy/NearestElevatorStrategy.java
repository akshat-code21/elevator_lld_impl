package org.example.strategy;

import org.example.models.Elevator;
import org.example.models.Floor;
import org.example.models.OutsideRequest;

import java.util.List;

public class NearestElevatorStrategy implements ElevatorCallStrategy {
    @Override
    public Elevator selectElevator(OutsideRequest or, List<Elevator> elevators) {
        // todo
        return null;
    }

    private int calculateDistance(Elevator e, Floor f) {
        return Math.abs(e.getCurrentFloor().getFloorNumber() - f.getFloorNumber());
    }
}