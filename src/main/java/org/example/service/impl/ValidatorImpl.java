package org.example.service.impl;

import org.example.model.Building;
import org.example.model.Elevator;
import org.example.model.Floor;
import org.example.model.Passenger;
import org.example.service.Validator;

public class ValidatorImpl implements Validator {
    @Override
    public void validateDirection(Building building) {
        int countPassengersNeededUp = 0;
        int countPassengersNeededDown = 0;
        Elevator elevator = building.getElevator();
        int currentFloorNumber = elevator.getCurrentFloor();
        Floor currentFloor = building.getFloorsInBuilding().get(currentFloorNumber - 1);
        if (elevator.getPassengers().size() == 0) {
            for (Passenger passenger : currentFloor.getPassengersOnFloor()) {
                if (passenger.getNeededFloor() > elevator.getCurrentFloor()) {
                    countPassengersNeededUp++;
                } else {
                    countPassengersNeededDown++;
                }
            }
            if (countPassengersNeededDown > countPassengersNeededUp) {
                elevator.setDirection(Elevator.Direction.DOWN);
            } else {
                elevator.setDirection(Elevator.Direction.UP);
            }
        }
        if (elevator.getCurrentFloor() == building.getFloorsInBuilding().size()) {
            elevator.setDirection(Elevator.Direction.DOWN);
        }
        if (elevator.getCurrentFloor() == 1) {
            elevator.setDirection(Elevator.Direction.UP);
        }
    }
}
