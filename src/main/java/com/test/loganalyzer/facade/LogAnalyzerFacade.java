package com.test.loganalyzer.facade;

import com.test.loganalyzer.db.EntryToEventConverter;
import com.test.loganalyzer.db.Event;
import com.test.loganalyzer.db.EventInfoDTO;
import com.test.loganalyzer.db.EventRepository;
import com.test.loganalyzer.file.LogEntry;
import com.test.loganalyzer.file.LogFileContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogAnalyzerFacade {

    private static Logger log = LoggerFactory.getLogger(LogAnalyzerFacade.class);
    private EntryToEventConverter converter;

    private final LogFileContentService fileContentService;
    private final EventRepository eventRepository;

    LogAnalyzerFacade(final LogFileContentService fileContentService, final EventRepository eventRepository) {
        this.fileContentService = fileContentService;
        this.eventRepository = eventRepository;

        converter = new EntryToEventConverter();
    }

    public void analyzeLogs(String fileName) throws Exception {
        log.info("Reading from file: " + fileName);
        List<LogEntry> logEntries = fileContentService.readFileContent(fileName);

        log.info("Processing file content of {} rows ", logEntries.size());
        List<EventInfoDTO> events = compactLog(logEntries);

        log.info("Content compacted to {} events - saving into database", events.size());
        saveData(events);

        log.info("Events in database: " + eventRepository.count());
    }

    private void saveData(final List<EventInfoDTO> eventInfos) {
        List<Event> entities = converter.convertToEntities(eventInfos);
        eventRepository.saveAll(entities);
    }

    private List<EventInfoDTO> compactLog(final List<LogEntry> logEntries) {
        return converter.convertEntriesToEventsMap(logEntries).values().stream().collect(Collectors.toList());
    }
}
