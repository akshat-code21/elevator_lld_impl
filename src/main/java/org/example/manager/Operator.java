package org.example.manager;

import org.example.models.Elevator;
import org.example.models.Floor;

public class Operator {
    private final String id;
    private final String name;
    private final ElevatorManager em;

    public Operator(String id, String name, ElevatorManager em) {
        this.id = id;
        this.name = name;
        this.em = em;
    }

    public void addFloor(Floor f){
        em.getFloors().add(f);
    }

    public void setUnderMaintenance(Elevator e){
        e.setUnderMaintenance();
    }
}
