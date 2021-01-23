package dev.pro.transport;

public class Skateboard extends TransportBase {
    private Person person;
    private boolean isStarted;

    protected Skateboard(Logger logger) {
        super(logger);
    }

    @Override
    public String getName() {
        return "Skateboard";
    }

    @Override
    public boolean occupy(Person person) {
        if(!checkCanOccupy(person)){
            return false;
        }
        this.person = person;
        return true;
    }

    @Override
    public boolean getIsFull() {
        return getIsOccupied();
    }

    @Override
    public boolean getIsOccupied() {
        return person != null;
    }

    @Override
    public boolean leave(Person person) {
        if(this.person != person){
            return false;
        }
        this.person = null;
        return true;
    }
}


