package pro.developer.java.core;

public interface Logger {
    void Log(String message);
    void LogFormat(String messageFormat, Object... args );
}
