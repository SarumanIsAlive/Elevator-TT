package org.example.service;

import org.example.model.Building;
import org.example.model.Floor;

public interface Generator {
    Building generateBuilding();

    Floor generateFloor(int maxFloorsInBuilding, int currentFloorNumber);

    int generateTargetFloorForPassenger(int maxFloorsInBuilding, int currentFloorNumber);
}
