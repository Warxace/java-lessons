package pro.developer.java.core;

import java.util.ArrayList;

public abstract class Vehicle extends TransportBase {
    protected final int capacity;
    protected ArrayList<Person> passengers;
    private float fuel;
    private boolean isStarted;

    Vehicle(Logger logger, int capacity) {
        super(logger);
        this.capacity = capacity;
        passengers = new ArrayList<>(capacity);
    }

    @Override
    public boolean occupy(Person person) {
        if (!checkCanOccupy(person))
            return false;

        passengers.add(person);
        return true;
    }

    @Override
    public boolean getIsFull() {
        return passengers.size() >= capacity;
    }

    @Override
    public boolean getIsOccupied() {
        return passengers.size() > 0;
    }

    @Override
    public boolean leave(Person person) {
        var personIdx = passengers.indexOf(person);
        if (personIdx < 0) {
            return false;
        }
        passengers.remove(personIdx);
        logger.LogFormat("%s is leaving the %s", person.getName(), this.getName());
        return true;
    }

    @Override
    protected  boolean checkCanStart() {
        if(!super.checkCanStart())
            return false;
        if (fuel < 0.1f) {
            logger.LogFormat("%s could not be started. There is no fuel.", this.getName());
            return false;
        }
        return true;
    }

    public void fillFuel(float fuelAmount) {
        fuel += fuelAmount;
    }
}

