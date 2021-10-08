package com.test.loganalyzer.db;

import com.test.loganalyzer.file.LogEntry;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntryToEventConverter {

    public static final int EVENT_DURATION_LIMIT_IN_MILLISECONDS = 4;

    public Map<String, EventInfoDTO> convertEntriesToEventsMap(final List<LogEntry> logEntries) {
        Map<String, EventInfoDTO> entriesMap = logEntries.stream()
                .collect(Collectors.toMap(LogEntry::getId,
                        this::createEvent,
                        this::mergeEvents));
        return entriesMap;
    }

    private EventInfoDTO createEvent(final LogEntry entry) {
        EventInfoDTO event = new EventInfoDTO();
        event.setId(entry.getId());
        event.setType(entry.getType());
        event.setHost(entry.getHost());
        switch (entry.getState()){
            case "STARTED":
                event.setStartedAt(entry.getTimestamp());
                break;
            case "FINISHED":
                event.setFinishedAt(entry.getTimestamp());
                break;
            default:
                break;
        }
        return event;
    }

    private EventInfoDTO mergeEvents(final EventInfoDTO first, final EventInfoDTO other) {
        first.fillUpMissingTimeData(other);

        Duration duration = Duration.between(first.startedAt, first.finishedAt);
        first.setDuration(duration);

        if (duration.compareTo(Duration.of(EVENT_DURATION_LIMIT_IN_MILLISECONDS, ChronoUnit.MILLIS)) > 0){
            first.setTooLong(true);
        }
        return first;
    }
}
