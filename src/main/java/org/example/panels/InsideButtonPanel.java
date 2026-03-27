package org.example.panels;

import org.example.buttons.AlarmButton;
import org.example.buttons.DoorsCloseButton;
import org.example.buttons.DoorsOpenButton;
import org.example.buttons.FloorButton;

import java.util.List;

public class InsideButtonPanel {
    private final List<FloorButton> floorButtons;
    private final DoorsOpenButton dob;
    private final DoorsCloseButton dcb;
    private final AlarmButton ab;

    public InsideButtonPanel(List<FloorButton> floorButtons, DoorsOpenButton dob, DoorsCloseButton dcb, AlarmButton ab) {
        this.floorButtons = floorButtons;
        this.dob = dob;
        this.dcb = dcb;
        this.ab = ab;
    }

    public List<FloorButton> getFloorButtons() {
        return floorButtons;
    }

    public DoorsOpenButton getDob() {
        return dob;
    }

    public DoorsCloseButton getDcb() {
        return dcb;
    }

    public AlarmButton getAb() {
        return ab;
    }
}
