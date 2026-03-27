package org.example.manager;

import org.example.models.Floor;

public class Operator {
    private String id;
    private String name;
    private ElevatorManager em;

    public Operator(String id, String name, ElevatorManager em) {
        this.id = id;
        this.name = name;
        this.em = em;
    }

    public void addFloor(Floor f){

    }

    public void setUnderMaintenance(){

    }
}
