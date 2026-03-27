package org.example.models;

import org.example.buttons.OutsideDownButton;
import org.example.buttons.OutsideUpButton;

public class Floor {
    private final int floorNumber;
    private final OutsideDownButton odb;
    private final OutsideUpButton oub;

    public Floor(int floorNumber, OutsideDownButton odb, OutsideUpButton oub) {
        this.floorNumber = floorNumber;
        this.odb = odb;
        this.oub = oub;
    }
}
