package org.example.strategy;

import org.example.enums.ElevatorState;
import org.example.models.Elevator;
import org.example.models.Floor;
import org.example.models.OutsideRequest;

import java.util.List;

public class NearestElevatorStrategy implements ElevatorCallStrategy {
    @Override
    public Elevator selectElevator(OutsideRequest or, List<Elevator> elevators) {
        Elevator best = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            if (e.getEs() == ElevatorState.UNDER_MAINTENANCE ||
                    e.getEs() == ElevatorState.ALARMED ||
                    e.getEs() == ElevatorState.OVERLOADED) {
                continue;
            }

            int distance = calculateDistance(e, or.getFloor());

            if (distance < minDistance) {
                minDistance = distance;
                best = e;
            }
        }

        return best;
    }

    private int calculateDistance(Elevator e, Floor f) {
        return Math.abs(e.getCurrentFloor().getFloorNumber() - f.getFloorNumber());
    }
}