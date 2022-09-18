package org.example.service.impl;

import org.example.service.Drawer;
import org.example.model.Building;
import org.example.model.Elevator;
import org.example.model.Floor;
import org.example.model.Passenger;
import org.example.service.ElevatorService;
import org.example.service.Generator;
import org.example.service.Validator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorServiceImpl implements ElevatorService, Drawer<Building> {
    private final Generator generator;
    private final Validator validator;

    public ElevatorServiceImpl(Generator generator, Validator validator) {
        this.generator = generator;
        this.validator = validator;
    }

    @Override
    public void runElevator(Building building) {
        Elevator elevator = building.getElevator();
        int counterOfIterations = 0;
        draw(building, counterOfIterations);
        while (counterOfIterations <= 49) {
            counterOfIterations++;
            int currentFloor = elevator.getCurrentFloor();
            if (elevator.getDirection() == Elevator.Direction.UP) {
                currentFloor++;
            } else {
                currentFloor--;
            }
            elevator.setCurrentFloor(currentFloor);
            putAwayPassengersFromElevator(building);
            validator.validateDirection(building);
            takePassengersInElevator(building);

            draw(building, counterOfIterations);
        }
    }

    @Override
    public void draw(Building building, int counter) {
        StringBuilder builder = new StringBuilder();
        Elevator elevator = building.getElevator();

        builder.append("*** Step ").append(counter).append(" ***").append(System.lineSeparator());

        int floorsNumberInBuilding = building.getFloorsInBuilding().size();
        for (int i = 0; i < floorsNumberInBuilding; i++) {
            int currentFloorNumber = floorsNumberInBuilding - i;
            String numberFormatted = String.format("%1$2d", currentFloorNumber);
            builder.append("Floor ").append(numberFormatted).append(" " + "| ");

            StringBuilder elevatorContent = new StringBuilder();
            if (elevator.getCurrentFloor() == currentFloorNumber) {
                elevatorContent.append(elevator.getDirection()).append(" ");
                for (Passenger passenger : elevator.getPassengers()) {
                    elevatorContent.append(passenger.getNeededFloor()).append(" ");
                }
            }

            String elevatorContentFormatted = String.format("%1$-20s", elevatorContent);
            builder.append(elevatorContentFormatted);
            builder.append("| ");

            Floor currentFloor = building.getFloorsInBuilding().get(currentFloorNumber - 1);
            for (Passenger passenger : currentFloor.getPassengersOnFloor()) {
                builder.append(passenger.getNeededFloor()).append(" ");
            }
            builder.append(System.lineSeparator());
        }
        System.out.println(builder);
    }

    private void takePassengersInElevator(Building building) {
        Elevator elevator = building.getElevator();
        int currentFloorNumber = elevator.getCurrentFloor();
        List<Passenger> passengerList = new ArrayList<>();
        Floor currentFloor = building.getFloorsInBuilding().get(currentFloorNumber - 1);
        if (elevator.getPassengers().size() < 5 && elevator.getDirection() == Elevator.Direction.UP) {
            for (Passenger passenger : currentFloor.getPassengersOnFloor()) {
                if (passenger.getNeededFloor() > currentFloorNumber
                        && passengerList.size() + elevator.getPassengers().size() < 5) {
                    passengerList.add(passenger);
                }
            }
            elevator.getPassengers().addAll(passengerList);
            currentFloor.getPassengersOnFloor().removeAll(passengerList);
        }
        if (elevator.getPassengers().size() < 5 && elevator.getDirection() == Elevator.Direction.DOWN) {
            for (Passenger passenger : currentFloor.getPassengersOnFloor()) {
                if (passenger.getNeededFloor() < currentFloorNumber
                        && passengerList.size() + elevator.getPassengers().size() < 5) {
                    passengerList.add(passenger);
                }
            }
            elevator.getPassengers().addAll(passengerList);
            currentFloor.getPassengersOnFloor().removeAll(passengerList);
        }
    }

    private void putAwayPassengersFromElevator(Building building) {
        List<Passenger> passengerList = new ArrayList<>();
        Elevator elevator = building.getElevator();
        int currentFloorNumber = elevator.getCurrentFloor();
        Floor currentFloor = building.getFloorsInBuilding().get(currentFloorNumber - 1);
        for (Passenger passenger : elevator.getPassengers()) {
            if (passenger.getNeededFloor() == currentFloorNumber) {
                passengerList.add(passenger);
            }
        }
        elevator.getPassengers().removeAll(passengerList);
        for (Passenger passenger : passengerList) {
            passenger.setNeededFloor(generator.generateTargetFloorForPassenger(
                            building.getFloorsInBuilding().size(),
                            currentFloorNumber));
        }
        currentFloor.getPassengersOnFloor().addAll(passengerList);
    }
}