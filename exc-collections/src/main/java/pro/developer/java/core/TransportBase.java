package pro.developer.java.core;

public abstract class TransportBase implements Transport {
    private boolean isStarted;
    protected final Logger logger;

    protected TransportBase(Logger logger) {
        this.logger = logger;
    }

    public boolean getIsStarted() {
        return isStarted;
    }

    @Override
    public boolean start() {
        if (!checkCanStart()) {
            return false;
        }
        isStarted = true;
        logger.LogFormat("%s is started", getName());
        return true;
    }


    @Override
    public boolean stop() {
        if (!isStarted) {
            logger.LogFormat("Could not stop the %s. Because it is not started.", getName());
            return false;
        }
        isStarted = false;
        logger.LogFormat("%s is stopped", getName());

        return true;
    }

    protected boolean checkCanOccupy(Person person) {
        if (isStarted) {
            logger.LogFormat("%s can not occupy the %s. Because it is started moving.", person.getName(), getName());
            return false;
        }

        if (getIsFull()) {
            logger.LogFormat("%s can not occupy %s. It is full", person.getName(), getName());
            return false;
        }
        return true;
    }

    protected boolean checkCanStart() {
        if (isStarted) {
            logger.LogFormat("%s is already started", this.getName());
            return false;
        }
        if (!getIsOccupied()) {
            logger.LogFormat("%s could not be started. Because it is empty.", this.getName());
            return false;
        }
        return true;
    }
}
