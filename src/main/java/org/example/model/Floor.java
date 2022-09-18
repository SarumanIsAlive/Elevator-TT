package org.example.model;

import java.util.List;

public class Floor {
    private List<Passenger> passengersOnFloor;
    private int floorNumber;

    public Floor(List<Passenger> passengersOnFloor, int floorNumber) {
        this.passengersOnFloor = passengersOnFloor;
        this.floorNumber = floorNumber;
    }

    public List<Passenger> getPassengersOnFloor() {
        return passengersOnFloor;
    }

    public void setPassengersOnFloor(List<Passenger> passengersOnFloor) {
        this.passengersOnFloor = passengersOnFloor;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    @Override
    public String toString() {
        return "Floor{" +
                ", floorNumber=" + floorNumber +
                ", passengersOnFloor=" + passengersOnFloor +
                '}';
    }
}
