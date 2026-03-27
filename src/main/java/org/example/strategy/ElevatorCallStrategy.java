package org.example.strategy;

import org.example.models.Elevator;
import org.example.models.OutsideRequest;

import java.util.List;

public interface ElevatorCallStrategy {
    Elevator selectElevator(OutsideRequest or, List<Elevator> elevators);
}
