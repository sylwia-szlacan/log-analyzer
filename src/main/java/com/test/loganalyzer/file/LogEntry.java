package com.test.loganalyzer.file;

import java.time.Instant;

public class LogEntry {
    String id;
    String state;
    Instant timestamp;
    String type;
    String host;

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public String getHost() {
        return host;
    }

    @Override
    public String toString() {
        return "LogEntry {" +
                "id='" + id + '\'' +
                ", state='" + state + '\'' +
                ", timestamp=" + timestamp +
                ", type='" + type + '\'' +
                ", host='" + host + '\'' +
                '}';
    }
}
