package dev.pro.transport;

public interface Logger {
    void Log(String message);
    void LogFormat(String messageFormat, Object... args );
}
