package org.example.panels;

import org.example.buttons.AlarmButton;
import org.example.buttons.DoorsCloseButton;
import org.example.buttons.DoorsOpenButton;
import org.example.buttons.FloorButton;

import java.util.List;

public class InsideButtonPanel {
    private List<FloorButton> floorButtons;
    private DoorsOpenButton dob;
    private DoorsCloseButton dcb;
    private AlarmButton ab;

    public InsideButtonPanel(List<FloorButton> floorButtons, DoorsOpenButton dob, DoorsCloseButton dcb, AlarmButton ab) {
        this.floorButtons = floorButtons;
        this.dob = dob;
        this.dcb = dcb;
        this.ab = ab;
    }
}
