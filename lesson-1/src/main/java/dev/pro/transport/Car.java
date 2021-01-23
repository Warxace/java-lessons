package dev.pro.transport;

import java.util.ArrayList;

public class Car extends Vehicle {

    public Car(Logger logger) {
        super(logger, 4);

    }

    @Override
    public String getName() {
        return "Car";
    }

}

