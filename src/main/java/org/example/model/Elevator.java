package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private Direction direction;
    private int currentFloor;
    private List<Passenger> passengers;

    public enum Direction {
        UP,
        DOWN
    }

    public Elevator() {
        this.direction = Direction.UP;
        this.passengers = new ArrayList<>(5);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                " direction=" + direction +
                ", currentFloor=" + currentFloor +
                ", passengers=" + passengers +
                '}';
    }
}
