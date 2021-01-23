package dev.pro.transport;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Transport transport;
    TestLogger logger;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        logger = new TestLogger();
        transport = getFueledCar();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
    static final String personName = "Veronika";

    private Transport getFueledCar(){
        var car = new Car(logger);
        car.fillFuel(1.0f);
        return car;
    }

    @org.junit.jupiter.api.Test
    void PersonConstructorTest() {
        var person = new Person(personName, logger);

        assertNotNull(person);

        assertEquals(personName, person.getName());

    }

    @org.junit.jupiter.api.Test
    void rideShouldLogMessage() {
        var person = new Person(personName, logger);
        assertFalse(person.getIsRiding());

        person.Ride(transport);
        assertTrue(person.getIsRiding());
        var expectedMessage = "Veronika started ride the Car";
        assertEquals(expectedMessage, logger.getMessage(1));
    }

    @org.junit.jupiter.api.Test
    void stopShouldLogMessage() {
        var person = new Person(personName, logger);
        person.Ride(transport);
        assertTrue(person.getIsRiding());
        logger.clear();

        person.Stop();
        var expectedMessage = "Veronika stopped ride the Car";
        assertEquals(expectedMessage, logger.getMessage(0));
        assertFalse(person.getIsRiding());
    }

    @org.junit.jupiter.api.Test
    void rideShouldOccupyTransport() {
        var person = new Person(personName, logger);
        assertFalse(transport.getIsOccupied());

        person.Ride(transport);
        assertTrue(transport.getIsOccupied());
    }

    @org.junit.jupiter.api.Test
    void personShouldNotRideFullyOccupiedTransport() {
        transport = new Skateboard(logger);
        String anotherPersonName = "Arina";
        var person2 = new Person(anotherPersonName, logger);
        person2.Ride(transport);
        assertTrue(transport.getIsFull());
        var person = new Person(personName, logger);
        logger.clear();

        person.Ride(transport);

        var expectedMessage = "Veronika can not ride the Skateboard, because it is full";
        assertEquals(expectedMessage, logger.getMessage(0));
    }

    @org.junit.jupiter.api.Test
    void personShouldNotRideAnotherTransportBeforeStopping() {
        var person = new Person(personName, logger);
        person.Ride(transport);
        assertTrue(person.getIsRiding());
        var anotherTransport = getFueledCar();
        logger.clear();

        person.Ride(anotherTransport);

        var expectedMessage = "Veronika can not ride the Car. Should stop first";
        assertEquals(expectedMessage, logger.getMessage(0));
    }
}

class TestLogger implements Logger{
    private boolean enabled;
    ArrayList<String> loggedMessages = new ArrayList<>();
    TestLogger(boolean enabled){
        this.enabled = enabled;

    }

    TestLogger(){
        this(true);
    }

    @Override
    public void Log(String message) {
        if (enabled) {
            loggedMessages.add(message);
        }
    }

    @Override
    public void LogFormat(String messageFormat, Object... args) {
        Log(String.format(messageFormat, args));
    }

    public String getMessage(int index){
        if (index >= loggedMessages.size()){
            return null;
        }
        return  loggedMessages.get(index);
    }

    public void clear(){
        loggedMessages.clear();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getEnabled() {
        return enabled;
    }
}