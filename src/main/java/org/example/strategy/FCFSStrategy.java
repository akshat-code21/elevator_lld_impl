package org.example.strategy;

import org.example.enums.ElevatorState;
import org.example.models.Elevator;
import org.example.models.OutsideRequest;

import java.util.List;

public class FCFSStrategy implements ElevatorCallStrategy{
    @Override
    public Elevator selectElevator(OutsideRequest or, List<Elevator> elevators) {
        for (Elevator e : elevators) {
            if (e.getEs() == ElevatorState.IDLE) {
                return e;
            }
        }

        for (Elevator e : elevators) {
            if (e.getEs() != ElevatorState.UNDER_MAINTENANCE &&
                    e.getEs() != ElevatorState.ALARMED &&
                    e.getEs() != ElevatorState.OVERLOADED) {
                return e;
            }
        }

        return null;
    }
}
