package org.example;

import org.example.buttons.*;
import org.example.enums.*;
import org.example.manager.*;
import org.example.models.*;
import org.example.panels.*;
import org.example.sensors.*;
import org.example.strategy.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ElevatorCallStrategy strategy = new NearestElevatorStrategy();

        List<Elevator> elevators = new ArrayList<>();
        List<Floor> floors = new ArrayList<>();
        ElevatorManager em = new ElevatorManager(elevators, floors, strategy);

        OutsideUpButton oub1 = new OutsideUpButton(null, em);
        OutsideDownButton odb1 = new OutsideDownButton(null, em);
        Floor floor1 = new Floor(1, odb1, oub1);
        oub1.setFloor(floor1);
        odb1.setFloor(floor1);

        OutsideUpButton oub2 = new OutsideUpButton(null, em);
        OutsideDownButton odb2 = new OutsideDownButton(null, em);
        Floor floor2 = new Floor(2, odb2, oub2);
        oub2.setFloor(floor2);
        odb2.setFloor(floor2);

        OutsideUpButton oub3 = new OutsideUpButton(null, em);
        OutsideDownButton odb3 = new OutsideDownButton(null, em);
        Floor floor3 = new Floor(3, odb3, oub3);
        oub3.setFloor(floor3);
        odb3.setFloor(floor3);

        em.addFloor(floor1);
        em.addFloor(floor2);
        em.addFloor(floor3);

        Door door1 = new Door(DoorStatus.DOOR_CLOSE);

        Elevator elevator1 = new Elevator(
                "E1",
                null,                
                ElevatorState.IDLE,
                floor1,              
                0.0,
                500.0,
                new ArrayList<>(),
                new ArrayList<>(),
                door1
        );
        elevators.add(elevator1);

        AlarmSensor alarmSensor1 = new AlarmSensor(elevator1);
        WeightSensor weightSensor1 = new WeightSensor(elevator1);

        DoorsOpenButton dob = new DoorsOpenButton(door1);
        DoorsCloseButton dcb = new DoorsCloseButton(door1);
        AlarmButton ab = new AlarmButton(alarmSensor1);

        List<FloorButton> floorButtons = new ArrayList<>();
        floorButtons.add(new FloorButton(floor1, elevator1));
        floorButtons.add(new FloorButton(floor2, elevator1));
        floorButtons.add(new FloorButton(floor3, elevator1));

        InsideButtonPanel ibp = new InsideButtonPanel(floorButtons, dob, dcb, ab);
        elevator1.setIbp(ibp);

        Operator operator = new Operator("OP1", "Alice", em);


        System.out.println("=== Scenario 1: Outside request from floor 2 going UP ===");
        oub2.press(); 

        System.out.println("\n=== Scenario 2: Inside request — passenger wants floor 3 ===");
        floorButtons.get(2).press();

        System.out.println("\n=== Scenario 3: Overload ===");
        weightSensor1.setCurrWeight(600.0);

        System.out.println("\n=== Scenario 4: Alarm triggered ===");
        ab.press();

        System.out.println("\n=== Scenario 5: Operator sets elevator under maintenance ===");
        operator.setUnderMaintenance(elevator1);

        System.out.println("\n=== Scenario 6: Operator adds a new floor ===");
        OutsideUpButton oub4 = new OutsideUpButton(null, em);
        OutsideDownButton odb4 = new OutsideDownButton(null, em);
        Floor floor4 = new Floor(4, odb4, oub4);
        oub4.setFloor(floor4);
        odb4.setFloor(floor4);
        operator.addFloor(floor4);
        System.out.println("Floor 4 added. Total floors: " + em.getNoOfFloors());
    }
}