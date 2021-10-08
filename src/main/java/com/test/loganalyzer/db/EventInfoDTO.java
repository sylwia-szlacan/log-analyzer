package com.test.loganalyzer.db;

import java.time.Duration;
import java.time.Instant;

public class EventInfoDTO {
    String id;
    Instant startedAt;
    Instant finishedAt;
    String type;
    String host;
    Duration duration;
    boolean tooLong;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(final Instant startedAt) {
        this.startedAt = startedAt;
    }

    Instant getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(final Instant finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public Duration getDuration() {
        return duration;
    }

    void setDuration(final Duration duration) {
        this.duration = duration;
    }

    public boolean isTooLong() {
        return tooLong;
    }

    void setTooLong(final boolean tooLong) {
        this.tooLong = tooLong;
    }

    public void fillUpMissingTimeData(EventInfoDTO other){
        if (this.startedAt != null){
            this.finishedAt = other.finishedAt;
        }
        else if (this.finishedAt != null){
            this.startedAt = other.startedAt;
        }
    }

    @Override
    public String toString() {
        return "EventInfoDTO {" +
                "id='" + id + '\'' +
                ", startedAt=" + startedAt +
                ", finishedAt=" + finishedAt +
                ", type='" + type + '\'' +
                ", host='" + host + '\'' +
                ", duration=" + duration +
                ", tooLong=" + tooLong +
                '}';
    }
}
