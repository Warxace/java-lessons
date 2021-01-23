package pro.developer.java.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    TestLogger logger;
    Car carUnderTest;
    static String[] personNames;
    int personIdx = 0;
    @BeforeEach
    void setUp() {
        logger = new TestLogger();
        carUnderTest = new Car(logger);
        carUnderTest.fillFuel(1.0f);
        personNames = new String[]{"Veronika" , "Arina", "Yegor", "Yaroslava"};
    }



    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        assertEquals("Car", carUnderTest.getName());
    }

    @Test
    void emptyCarCouldBeOccupied() {
        var person = getPerson();
        assertFalse(carUnderTest.getIsOccupied());

        var result = carUnderTest.occupy(person);

        assertTrue(result);
    }

    @Test
    void fullCarCouldNotBeOccupied(){
        carUnderTest.occupy(getPerson());
        carUnderTest.occupy(getPerson());
        carUnderTest.occupy(getPerson());
        carUnderTest.occupy(getPerson());
        var person = getPerson();
        logger.clear();

        var result = carUnderTest.occupy(person);

        assertFalse(result);
        var expectedMessage = "Veronika can not occupy Car. It is full";
        assertEquals(expectedMessage, logger.getMessage(0));
    }

    private Person getPerson() {
        var idx = personIdx++ % personNames.length;
        return new Person(personNames[idx], logger);
    }

    @Test
    void leaveShouldRemovePersonFromCar() {
        var person = getPerson();
        carUnderTest.occupy(person);
        assertTrue(carUnderTest.getIsOccupied());

        carUnderTest.leave(person);

        assertFalse(carUnderTest.getIsOccupied());
        var expectedMessage = "Veronika is leaving the Car";
        assertEquals(expectedMessage,logger.getMessage(0));

    }

    @Test
    void shouldNotStartUnoccupiedCar(){
        assertFalse(carUnderTest.getIsOccupied());

        var started = carUnderTest.start();

        assertFalse(started);
        var expectedMessage = "Car could not be started. Because it is empty.";
        assertEquals(expectedMessage, logger.getMessage(0));
    }

    @Test
    void shouldNotStartCarWithoutFuel(){
        carUnderTest = new Car(logger); //get car without fuel
        carUnderTest.occupy(getPerson());
        var started = carUnderTest.start();

        assertFalse(started);
        var expectedMessage = "Car could not be started. There is no fuel.";
        assertEquals(expectedMessage, logger.getMessage(0));
    }

    @Test
    void shouldStartOccupiedAndFueledCar(){
        carUnderTest.occupy(getPerson());
        assertTrue(carUnderTest.getIsOccupied());

        var started = carUnderTest.start();

        assertTrue(started);
        var expectedMessage = "Car is started";
        assertEquals(expectedMessage, logger.getMessage(0));
    }

    @Test
    void shouldNotOccupyStartedCar() {
        var person1 = getPerson();
        carUnderTest.occupy(person1);
        carUnderTest.start();
        var person2 = getPerson();
        logger.clear();

        var result = carUnderTest.occupy(person2);

        assertFalse(result);
        var expectedMessage = "Arina can not occupy the Car. Because it is started moving.";
        assertEquals(expectedMessage, logger.getMessage(0));
    }

    @Test
    void shouldNotStartCarIfAlreadyStarted(){
        var person1 = getPerson();
        carUnderTest.occupy(person1);
        carUnderTest.start();
        assertTrue(carUnderTest.getIsStarted());
        logger.clear();

        var result = carUnderTest.start();
        assertFalse(result);
        var expectedMessage = "Car is already started";
        assertEquals(expectedMessage, logger.getMessage(0));
    }


    @Test
    void couldNotStopCarIfNotStarted() {
        assertFalse(carUnderTest.getIsStarted());

        var result = carUnderTest.stop();

        assertFalse(result);
        var expectedMessage = "Could not stop the Car. Because it is not started.";
        assertEquals(expectedMessage, logger.getMessage(0));
    }

    @Test
    void shouldStopStartedCar(){
        var person = getPerson();
        carUnderTest.occupy(person);
        carUnderTest.start();
        assertTrue(carUnderTest.getIsStarted());
        logger.clear();

        var result = carUnderTest.stop();

        assertTrue(result);
        assertFalse(carUnderTest.getIsStarted());
        var expectedMessage = "Car is stopped";
        assertEquals(expectedMessage,logger.getMessage(0));
    }
}