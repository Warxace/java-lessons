package pro.developer.java.core;

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

