package pro.developer.java.core;

public interface Transport {
    String getName();

    boolean occupy(Person person);

    boolean getIsFull();

    boolean getIsOccupied();

    boolean leave(Person person);

    boolean start();

    boolean stop();

    boolean getIsStarted();
}

