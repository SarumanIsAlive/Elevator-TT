package org.example.model;

import java.util.Objects;
import java.util.UUID;

public class Passenger {
    private final UUID id;
    private int neededFloor;

    public Passenger(int neededFloor) {
        this.neededFloor = neededFloor;
        this.id = UUID.randomUUID();
    }

    public int getNeededFloor() {
        return neededFloor;
    }

    public void setNeededFloor(int neededFloor) {
        this.neededFloor = neededFloor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return neededFloor == passenger.neededFloor && Objects.equals(id, passenger.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, neededFloor);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "neededFloor=" + neededFloor +
                '}';
    }
}
