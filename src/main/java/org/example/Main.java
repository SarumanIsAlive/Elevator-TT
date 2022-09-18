package org.example;

import org.example.model.Building;
import org.example.service.ElevatorService;
import org.example.service.Generator;
import org.example.service.impl.ElevatorServiceImpl;
import org.example.service.impl.GeneratorImpl;
import org.example.service.impl.ValidatorImpl;

public class Main {
    public static void main(String[] args) {
        Generator buildingGenerator = new GeneratorImpl();
        Building building = buildingGenerator.generateBuilding();

        ElevatorService elevator = new ElevatorServiceImpl(buildingGenerator, new ValidatorImpl());
        elevator.runElevator(building);
    }
}
