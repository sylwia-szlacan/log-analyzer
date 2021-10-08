package com.test.loganalyzer.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event {
    @Id
    String id;
    String type;
    String host;
    long duration;
    boolean alert;

    Event() {
    }

    public Event(final String id, final String type, final String host, final long duration, final boolean alert) {
        this.id = id;
        this.type = type;
        this.host = host;
        this.duration = duration;
        this.alert = alert;
    }
}
