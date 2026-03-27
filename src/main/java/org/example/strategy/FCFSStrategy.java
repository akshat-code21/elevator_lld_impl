package org.example.strategy;

import org.example.models.Elevator;
import org.example.models.OutsideRequest;

import java.util.List;

public class FCFSStrategy implements ElevatorCallStrategy{
    @Override
    public Elevator selectElevator(OutsideRequest or, List<Elevator> elevators) {
        // todo
        return null;
    }
}
