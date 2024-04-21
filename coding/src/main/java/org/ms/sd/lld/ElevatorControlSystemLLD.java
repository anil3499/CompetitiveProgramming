package org.ms.sd.lld;/*
package ms.sd.lld;
*/
/*
Algo :
1. SCAN (with 2 array or queue one for up direction and other for down direction)
2. LOOK (look ahead + SCAN)

https://www.javastructures.com/design/elevator
https://github.com/joeblau/sample-elevator-control-system/tree/master/src/main/java/com/joeblau/ecs/interfaces
Design
The elevator control system has two classes, an Elevator and an ElevatorControlSystem

ElevatorControlSystem
The Elevator control system manages all of the elevators and process the next tick of the system.
It is responsible for handling pickup requests which come from people outside of the elevator
and destination requests which come from users inside the elevator

Elevator
The Elevator manages which floor it's on, which floors it needs to go to, the status,
and the direction of the elevator.
 *//*


import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

enum ElevatorDirection {
    ELEVATOR_UP,
    ELEVATOR_DOWN,
    ELEVATOR_HOLD
}

enum ElevatorStatus {
    ELEVATOR_OCCUPIED,
    ELEVATOR_EMPTY;
}

//Added pickup and destination. Pickup is when a user is outside
// and destination is when a user is inside
interface ElevatorControlSystemFactory {
    public void pickUp(Integer pickUpFloor);
    public void destination(Integer elevatorId, Integer destinationFloor);
    public void step();

}
//Added step logic based on elevators direction
interface ElevatorFactory {
    public void moveUp();
    public void moveDown();
    public void addNewDestinatoin(Integer destination);
    public ElevatorDirection direction();
    public ElevatorStatus status();

}

class ElevatorControlSystem implements ElevatorControlSystemFactory {

    public static final int MAX_ELEVATORS = 16;
    Integer numberOfElevators = 0;
    Integer numberOfFloors = 0;
    ArrayList<Elevator> elevators;
    Queue<Integer> pickupLocations;

    public ElevatorControlSystem(Integer numberOfElevators, Integer numberOfFloors) throws InvalidNumber {
        if (numberOfElevators < 0) throw new InvalidNumber("Elevator number must be positive");
        this.numberOfElevators = (numberOfElevators > MAX_ELEVATORS)?MAX_ELEVATORS:numberOfElevators;
        this.numberOfFloors = numberOfFloors;
        initializeElevators();
        pickupLocations = new LinkedList<Integer>();
    }

    private void initializeElevators(){
        elevators = new ArrayList<Elevator>();
        for (int idx=0;idx<this.numberOfElevators;idx++){
            elevators.add(new Elevator(1));
        }
    }

    public ArrayList<Elevator> getElevators(){
        return elevators;
    }

    @Override
    public void pickUp(Integer pickUpFloor) {
        pickupLocations.add(pickUpFloor);
    }

    @Override
    public void destination(Integer elevatorId, Integer destinationFloor) {
        elevators.get(elevatorId).addNewDestinatoin(destinationFloor);
    }

    @Override
    public void step() {
        // Loop though every elevator
        for (Elevator currElevator : elevators){
            // Check to figure out which ones are unoccupied and update call
            switch (currElevator.status()){
                case ELEVATOR_EMPTY:
                    if (!pickupLocations.isEmpty())
                        currElevator.addNewDestinatoin(pickupLocations.poll());
                    break;
                // Move occupied Elevators one step closer to next closest destination in direction
                case ELEVATOR_OCCUPIED:
                    switch (currElevator.direction()){
                        case ELEVATOR_UP:
                            currElevator.moveUp();
                            break;
                        case ELEVATOR_DOWN:
                            currElevator.moveDown();
                            break;
                        case ELEVATOR_HOLD:
                            // TODO: Check timer here to alert users that they are holding the door open to long
                            // TODO: Emergency situation where elevator can't be used
                            // TODO: Maintenance Mode e.g. movers or maintenance people
                            currElevator.popDestination();
                            break;
                    }
                    if (currElevator.direction() == ElevatorDirection.ELEVATOR_UP)
                        break;
            }
        }
    }
}

class Elevator implements ElevatorFactory {
    private Integer currentFloor;
    private Queue<Integer> destinationFloors;

    public Elevator(Integer currentFloor) {
        this.currentFloor = currentFloor;
        this.destinationFloors = new LinkedList<Integer>();
    }

    public int nextDestionation(){
        return this.destinationFloors.peek();
    }

    public int currentFloor(){
        return this.currentFloor;
    }

    public void popDestination(){
        this.destinationFloors.remove();
    }
    @Override
    public void addNewDestinatoin(Integer destination) {
        this.destinationFloors.add(destination);
    }

    @Override
    public void moveUp() {
        currentFloor++;
    }

    @Override
    public void moveDown() {
        currentFloor--;
    }

    @Override
    public ElevatorDirection direction() {
        if (destinationFloors.size() > 0){
            if (currentFloor < destinationFloors.peek()){
                return ElevatorDirection.ELEVATOR_UP;
            } else if (currentFloor > destinationFloors.peek()) {
                return ElevatorDirection.ELEVATOR_DOWN;
            }
        }
        return ElevatorDirection.ELEVATOR_HOLD;
    }

    @Override
    public ElevatorStatus status() {
        return (destinationFloors.size() > 0)?ElevatorStatus.ELEVATOR_OCCUPIED:ElevatorStatus.ELEVATOR_EMPTY;
    }
}

class InvalidNumber extends Exception {
    public InvalidNumber(String msg) {
        super(msg);
    }
}


class ElevatorControlSystemTest {
    public static final int ELEVATOR_ID_ZERO = 0;
    public static final int ELEVATOR_ID_ONE = 1;
    public static final int TENTH_FLOOR = 10;
    public static final int FIRST_FLOOR = 1;
    public static final int SEVENTH_FLOOR = 8;
    private ElevatorControlSystem elevatorControlSystem;
    private ArrayList<Elevator> elevators;
    @Before
    public void initialize(){
        try {
            elevatorControlSystem = new ElevatorControlSystem(2, 20);
        } catch (InvalidNumber invalidNumber) {
            invalidNumber.printStackTrace();
        }
    }

    @Test
    public void testRequestingOneElevator(){
        elevatorControlSystem.pickUp(TENTH_FLOOR);
        for(int idx=0;idx<TENTH_FLOOR;idx++){
            elevatorControlSystem.step();
        }
        elevators = elevatorControlSystem.getElevators();
        assertEquals(TENTH_FLOOR, elevators.get(ELEVATOR_ID_ZERO).currentFloor());
    }
    @Test
    public void testElevatorTwoNotMoving(){
        elevatorControlSystem.pickUp(TENTH_FLOOR);
        for(int idx=0;idx<TENTH_FLOOR;idx++){
            elevatorControlSystem.step();
        }
        elevators = elevatorControlSystem.getElevators();
        assertEquals(FIRST_FLOOR, elevators.get(ELEVATOR_ID_ONE).currentFloor());
    }

    @Test
    public void testCallingTwoElevators(){
        elevatorControlSystem.pickUp(TENTH_FLOOR);
        elevatorControlSystem.pickUp(SEVENTH_FLOOR);
        for(int idx=0;idx<TENTH_FLOOR;idx++){
            elevatorControlSystem.step();
        }
        elevators = elevatorControlSystem.getElevators();
        assertEquals(TENTH_FLOOR, elevators.get(ELEVATOR_ID_ZERO).currentFloor());
        assertEquals(SEVENTH_FLOOR, elevators.get(ELEVATOR_ID_ONE).currentFloor());
    }

    @Test
    public void testSendingElevatorToDestination(){
        elevatorControlSystem.destination(ELEVATOR_ID_ZERO, TENTH_FLOOR);
        for(int idx=0;idx<TENTH_FLOOR;idx++){
            elevatorControlSystem.step();
        }
        elevators = elevatorControlSystem.getElevators();
        assertEquals(TENTH_FLOOR, elevators.get(ELEVATOR_ID_ZERO).currentFloor());
    }

    @Test
    public void testSendingElevatorToMultipleDestinations(){
        elevatorControlSystem.destination(ELEVATOR_ID_ZERO, TENTH_FLOOR);
        elevatorControlSystem.destination(ELEVATOR_ID_ZERO, SEVENTH_FLOOR);
        for(int idx=0;idx<TENTH_FLOOR;idx++){
            elevatorControlSystem.step();
        }
        elevators = elevatorControlSystem.getElevators();
        assertEquals(TENTH_FLOOR, elevators.get(ELEVATOR_ID_ZERO).currentFloor());
        for(int idx=0;idx<TENTH_FLOOR-SEVENTH_FLOOR;idx++){
            elevatorControlSystem.step();
        }
        elevators = elevatorControlSystem.getElevators();
        assertEquals(SEVENTH_FLOOR, elevators.get(ELEVATOR_ID_ZERO).currentFloor());
    }

}


class ElevatorTest {
    public static final int TENTH_FLOOR = 10;
    public static final int SECOND_FLOOR = 2;
    public static final int BASEMENT_TWO = -2;

    private Elevator elevator;

    @Before
    public void initializeElevator(){
        elevator = new Elevator(0);
    }

    @Test
    public void testAddingDestination(){
        elevator.addNewDestinatoin(TENTH_FLOOR);
        assertEquals(TENTH_FLOOR, elevator.nextDestionation());
    }

    @Test
    public void checkCurrentFloor(){
        // Move to floor 2
        elevator.moveUp();
        elevator.moveUp();
        assertEquals(SECOND_FLOOR, elevator.currentFloor());
    }

    @Test
    public void checkMoveDown(){
        elevator.moveDown();
        elevator.moveDown();
        assertEquals(BASEMENT_TWO, elevator.currentFloor());
    }

    @Test
    public void checkDirectionUp(){
        elevator.addNewDestinatoin(SECOND_FLOOR);
        assertEquals(ElevatorDirection.ELEVATOR_UP, elevator.direction());
    }

    @Test
    public void checkDirectionDown(){
        elevator.addNewDestinatoin(BASEMENT_TWO);
        assertEquals(ElevatorDirection.ELEVATOR_DOWN, elevator.direction());
    }

    @Test
    public void checkDirectionHold(){
        assertEquals(ElevatorDirection.ELEVATOR_HOLD, elevator.direction());
    }

    @Test
    public void checkStatusEmpty(){
        assertEquals(ElevatorStatus.ELEVATOR_EMPTY, elevator.status());
    }

    @Test
    public void checkStatusOccupied(){
        elevator.addNewDestinatoin(TENTH_FLOOR);
        assertEquals(ElevatorStatus.ELEVATOR_OCCUPIED, elevator.status());
    }
}



public class ElevatorControlSystemLLD {
}
*/
