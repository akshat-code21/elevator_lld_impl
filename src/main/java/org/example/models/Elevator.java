package org.example.models;

import org.example.enums.Direction;
import org.example.enums.ElevatorState;
import org.example.panels.InsideButtonPanel;

import java.util.List;

public class Elevator implements InsideRequestListener,WeightSensorListener,AlarmSensorListener{
    private final String id;
    private InsideButtonPanel ibp;
    private ElevatorState es;
    private Floor currentFloor;
    private Direction currentDirection;
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
        this.currentDirection = Direction.UP;
        this.currentWeight = currentWeight;
        this.maxWeight = maxWeight;
        this.insideRequests = insideRequests;
        this.outsideRequests = outsideRequests;
        this.d = d;
    }

    public void insideRequest(Floor f){
        insideRequests.add(f);
        move();
    }

    public void move(){
        if (es == ElevatorState.UNDER_MAINTENANCE || es == ElevatorState.ALARMED) return;
        if (es == ElevatorState.OVERLOADED) {
            triggerAlarm();
            return;
        }

        if (insideRequests.isEmpty() && outsideRequests.isEmpty()) {
            es = ElevatorState.IDLE;
            return;
        }

        if (es == ElevatorState.IDLE) {
            currentDirection = determineInitialDirection();
        }

        Floor nextStop = findNextStopInDirection(currentDirection);

        if (nextStop == null) {
            currentDirection = (currentDirection == Direction.UP) ? Direction.DOWN : Direction.UP;
            nextStop = findNextStopInDirection(currentDirection);
        }

        if (nextStop == null) {
            es = ElevatorState.IDLE;
            return;
        }

        es = (currentDirection == Direction.UP) ? ElevatorState.MOVING_UP : ElevatorState.MOVING_DOWN;
        System.out.println("Elevator " + id + " moving " + currentDirection + " from floor " + currentFloor.getFloorNumber());

        moveToFloor(nextStop);
        stopAtCurrentFloor();

        if (insideRequests.isEmpty() && outsideRequests.isEmpty()) {
            es = ElevatorState.IDLE;
            System.out.println("Elevator " + id + " is now IDLE at floor " + currentFloor.getFloorNumber());
        }
    }

    private Direction determineInitialDirection() {
        int current = currentFloor.getFloorNumber();
        int closestAbove = Integer.MAX_VALUE;
        int closestBelow = Integer.MIN_VALUE;

        for (Floor f : insideRequests) {
            int fn = f.getFloorNumber();
            if (fn > current) closestAbove = Math.min(closestAbove, fn);
            if (fn < current) closestBelow = Math.max(closestBelow, fn);
        }
        for (OutsideRequest or : outsideRequests) {
            int fn = or.getFloor().getFloorNumber();
            if (fn > current) closestAbove = Math.min(closestAbove, fn);
            if (fn < current) closestBelow = Math.max(closestBelow, fn);
        }

        int distUp = (closestAbove == Integer.MAX_VALUE) ? Integer.MAX_VALUE : closestAbove - current;
        int distDown = (closestBelow == Integer.MIN_VALUE) ? Integer.MAX_VALUE : current - closestBelow;

        return (distUp <= distDown) ? Direction.UP : Direction.DOWN;
    }

    private Floor findNextStopInDirection(Direction dir) {
        int current = currentFloor.getFloorNumber();
        Floor best = null;
        int bestFloorNum = (dir == Direction.UP) ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        for (Floor f : insideRequests) {
            int fn = f.getFloorNumber();
            if (dir == Direction.UP && fn >= current && fn < bestFloorNum) {
                bestFloorNum = fn;
                best = f;
            } else if (dir == Direction.DOWN && fn <= current && fn > bestFloorNum) {
                bestFloorNum = fn;
                best = f;
            }
        }

        for (OutsideRequest or : outsideRequests) {
            int fn = or.getFloor().getFloorNumber();
            if (dir == Direction.UP && fn >= current && fn < bestFloorNum) {
                bestFloorNum = fn;
                best = or.getFloor();
            } else if (dir == Direction.DOWN && fn <= current && fn > bestFloorNum) {
                bestFloorNum = fn;
                best = or.getFloor();
            }
        }

        return best;
    }

    private void moveToFloor(Floor target) {
        int targetNum = target.getFloorNumber();
        int current = currentFloor.getFloorNumber();

        while (current != targetNum) {
            current += (currentDirection == Direction.UP) ? 1 : -1;
            System.out.println("Elevator " + id + " passing floor " + current);
        }

        currentFloor = target;
    }

    private void stopAtCurrentFloor() {
        int current = currentFloor.getFloorNumber();
        System.out.println("Elevator " + id + " stopped at floor " + current);

        d.open();
        insideRequests.removeIf(f -> f.getFloorNumber() == current);
        outsideRequests.removeIf(or -> or.getFloor().getFloorNumber() == current);
        d.close();
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

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void outsideRequest(OutsideRequest or) {
        outsideRequests.add(or);
        move();
    }

    @Override
    public void onInsideRequest(Floor f) {
        insideRequest(f);
    }

    public ElevatorState getEs() {
        return es;
    }

    public void setIbp(InsideButtonPanel ibp) {
        this.ibp = ibp;
    }
}
