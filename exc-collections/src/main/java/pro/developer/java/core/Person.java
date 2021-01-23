package pro.developer.java.core;

public class Person {
    String name;
    private Logger logger;
    private Transport occupiedTransport = null;


    public Person(String name, Logger logger){
        this.name = name;
        this.logger = logger;
    }
    public String getName() {
        return name;
    }

    public boolean getIsRiding() {
        return occupiedTransport != null && occupiedTransport.getIsOccupied();
    }

    public void Ride(Transport transport){
        if(getIsRiding()){
            logger.LogFormat("%s can not ride the %s. Should stop first", name, transport.getName());
            return;
        }

        if (transport.getIsFull()){
            logger.LogFormat("%s can not ride the %s, because it is full", name, transport.getName());
            return;
        }
        var occupied = transport.occupy(this);
        if(!occupied){
            logger.Log("Something went wrong");
            return;
        }
        occupiedTransport = transport;
        var started = occupiedTransport.start();
        if(started){
            logger.Log(name + " started ride the " + occupiedTransport.getName());
        }
        else {
            logger.LogFormat("%s can not start ride %s", name, occupiedTransport.getName());
        }
    }

    public void Stop(){

        logger.Log(name + " stopped ride the " + occupiedTransport.getName());
        occupiedTransport = null;
    }


}

