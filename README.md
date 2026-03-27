# Elevator System — Low Level Design

A Java implementation of a real-world elevator system, designed using core OOP principles and common design patterns including **Strategy** and **Observer**.


---

## UML Diagram

![Elevator System UML Diagram](Elevator_drawio.png)

--

## Design Patterns Used

| Pattern | Where | Why |
|---|---|---|
| **Strategy** | `ElevatorCallStrategy`, `FCFSStrategy`, `NearestElevatorStrategy` | Swap dispatch algorithm at runtime without changing `ElevatorManager` |
| **Observer** | `WeightSensorListener`, `AlarmSensorListener`, `InsideRequestListener`, `OutsideRequestListener` | Sensors and buttons notify elevator/manager without tight coupling |

---

## Project Structure

```
src/main/java/org/example/
├── Main.java
├── buttons/
│   ├── Button.java                  # abstract base
│   ├── AlarmButton.java
│   ├── DoorsOpenButton.java
│   ├── DoorsCloseButton.java
│   ├── FloorButton.java
│   ├── OutsideUpButton.java
│   └── OutsideDownButton.java
├── enums/
│   ├── Direction.java               # UP, DOWN
│   ├── DoorStatus.java              # DOOR_OPEN, DOOR_CLOSE, DOOR_OPENING
│   └── ElevatorState.java           # IDLE, MOVING_UP, MOVING_DOWN, UNDER_MAINTENANCE, OVERLOADED, ALARMED
├── manager/
│   ├── ElevatorManager.java         # dispatches requests using strategy
│   └── Operator.java                # adds floors, sets maintenance
├── models/
│   ├── Elevator.java                # core entity
│   ├── Floor.java
│   ├── Door.java
│   ├── OutsideRequest.java          # floor + direction
│   ├── InsideRequestListener.java
│   ├── OutsideRequestListener.java
│   ├── WeightSensorListener.java
│   └── AlarmSensorListener.java
├── panels/
│   └── InsideButtonPanel.java       # groups all inside buttons
├── sensors/
│   ├── WeightSensor.java
│   └── AlarmSensor.java
└── strategy/
    ├── ElevatorCallStrategy.java    # interface
    ├── FCFSStrategy.java            # first idle elevator wins
    └── NearestElevatorStrategy.java # closest elevator by floor distance
```

---

## Setup Steps

### Prerequisites

- Java 22+
- Maven 3.8+

### Clone and build

```bash
git clone https://github.com/akshat-code21/elevator_lld_impl
cd elevator-lld
mvn clean compile
```

### Run

```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

Or run `Main.java` directly from IntelliJ IDEA by clicking the Run button next to `main()`.

### Switch dispatch strategy

In `Main.java`, change the strategy on the first line:

```java
// Nearest elevator by floor distance (default)
ElevatorCallStrategy strategy = new NearestElevatorStrategy();

// First-come-first-served
ElevatorCallStrategy strategy = new FCFSStrategy();
```

---

## Sample Interaction

The `Main.java` simulates 6 scenarios against a building with 3 floors and 1 elevator (max weight 500 kg).

### Scenario 1 — Outside request from floor 2 going UP

A person on floor 2 presses the UP button. `ElevatorManager` uses the active strategy to select an elevator and adds the request to its outside queue.

```
=== Scenario 1: Outside request from floor 2 going UP ===
// NearestElevatorStrategy selects elevator E1
// OutsideRequest(floor=2, direction=UP) added to E1's outsideRequests queue
```

### Scenario 2 — Inside request from passenger

A passenger inside the elevator presses the button for floor 3.

```
=== Scenario 2: Inside request — passenger wants floor 3 ===
// FloorButton(floor=3).press() called
// InsideRequestListener.onInsideRequest(floor3) fires on Elevator
// Floor 3 added to E1's insideRequests queue
```

### Scenario 3 — Overload detected

The weight sensor detects 600 kg, exceeding the 500 kg limit.

```
=== Scenario 3: Overload ===
// WeightSensor.setCurrWeight(600.0) called
// WeightSensorListener.updateWeight(600.0) fires on Elevator
// checkOverWeight() → true
// ElevatorState → OVERLOADED
// Door opened — passengers must exit
// playSong() called
```

### Scenario 4 — Alarm triggered

A passenger presses the alarm button inside the elevator.

```
=== Scenario 4: Alarm triggered ===
// AlarmButton.press() called
// AlarmSensor.alarmButtonPressed() fires
// AlarmSensorListener.triggerAlarm() fires on Elevator
// ElevatorState → ALARMED
ALARM TRIGGERED — Elevator stopped
```

### Scenario 5 — Operator sets maintenance mode

The operator takes elevator E1 offline for servicing.

```
=== Scenario 5: Operator sets elevator under maintenance ===
// Operator.setUnderMaintenance(elevator1) called
// Elevator.setUnderMaintenance() fires
// ElevatorState → UNDER_MAINTENANCE
// E1 skipped in all future strategy selections
```

### Scenario 6 — Operator adds a new floor

The operator adds floor 4 to the building.

```
=== Scenario 6: Operator adds a new floor ===
// Operator.addFloor(floor4) → ElevatorManager.addFloor(floor4)
Floor 4 added. Total floors: 4
```

---
