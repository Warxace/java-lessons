package dev.pro.transport;

public class MainApp {
    static final Logger logger = new ConsoleLoger();
    public static void main(String[] args) {

        Person person = new Person("Veronika", logger);

        Car car = new Car(logger);
        car.fillFuel(1.0f);
        person.Ride(car);
    }

}

class ConsoleLoger implements Logger {

    @Override
    public void Log(String message) {
        System.out.println(message);
    }

    @Override
    public void LogFormat(String messageFormat, Object... args) {
        System.out.println(String.format(messageFormat, args));
    }
}