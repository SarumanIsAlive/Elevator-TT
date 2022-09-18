package org.example.model;

import java.util.List;

public class Building {
    private Elevator elevator;
    private List<Floor> floorsInBuilding;

    public Building(Elevator elevator, List<Floor> floorsInBuilding) {
        this.elevator = elevator;
        this.floorsInBuilding = floorsInBuilding;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public List<Floor> getFloorsInBuilding() {
        return floorsInBuilding;
    }

    public void setFloorsInBuilding(List<Floor> floorsInBuilding) {
        this.floorsInBuilding = floorsInBuilding;
    }

    @Override
    public String toString() {
        return "Building{" +
                "elevator=" + elevator +
                ", floorsInBuilding=" + floorsInBuilding +
                '}';
    }
}
