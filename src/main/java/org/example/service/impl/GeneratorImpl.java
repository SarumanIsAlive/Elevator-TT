package org.example.service.impl;

import org.example.model.Building;
import org.example.model.Elevator;
import org.example.model.Floor;
import org.example.model.Passenger;
import org.example.service.Generator;

import java.util.ArrayList;
import java.util.Random;

public class GeneratorImpl implements Generator {
    private static final int MIN_FLOORS_COUNT = 5;
    private static final int MAX_FLOORS_COUNT = 20;
    private static final int MAX_PASSENGERS_COUNT = 10;

    @Override
    public Building generateBuilding() {
        int randomNumberFloorsInBuilding = generateRandomNumberBetween(MIN_FLOORS_COUNT, MAX_FLOORS_COUNT);
        Building building = new Building(new Elevator(), new ArrayList<>());
        for (int i = 0; i < randomNumberFloorsInBuilding; i++) {
            building.getFloorsInBuilding().add(generateFloor(randomNumberFloorsInBuilding, i + 1));
        }
        return building;
    }

    @Override
    public Floor generateFloor(int maxFloorsInBuilding, int currentFloorNumber) {
        Floor floor = new Floor(new ArrayList<>(), currentFloorNumber);
        int randomNumberOfPassengersInFloor = generateRandomNumberBetweenWithZero(MAX_PASSENGERS_COUNT);
        for (int i = 0; i < randomNumberOfPassengersInFloor; i++) {
            int targetFloor = generateTargetFloorForPassenger(maxFloorsInBuilding, currentFloorNumber);
            Passenger passenger = new Passenger(targetFloor);
            floor.getPassengersOnFloor().add(passenger);
        }
        return floor;
    }

    @Override
    public int generateTargetFloorForPassenger(int maxFloorsInBuilding, int currentFloorNumber) {
        int result = generateRandomNumberBetween(1, maxFloorsInBuilding);
        while (result == currentFloorNumber) {
            result = generateRandomNumberBetween(1, maxFloorsInBuilding);
        }
        return result;
    }

    private int generateRandomNumberBetween(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    private int generateRandomNumberBetweenWithZero(int max) {
        return (int) (Math.random() * (max + 1));
    }
}
