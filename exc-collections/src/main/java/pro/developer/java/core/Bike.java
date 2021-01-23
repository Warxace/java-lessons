package pro.developer.java.core;

public class Bike extends Vehicle {
    public Bike(Logger logger) {
        super(logger, 2);
    }

    @Override
    public String getName() {
        return "Bike";
    }
}
