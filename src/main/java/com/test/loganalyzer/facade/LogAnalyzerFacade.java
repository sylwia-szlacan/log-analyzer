package com.test.loganalyzer.facade;

import com.test.loganalyzer.db.EntryToEventConverter;
import com.test.loganalyzer.db.EventInfoDTO;
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
    LogFileContentService fileContentService;

    LogAnalyzerFacade(final LogFileContentService fileContentService) {
        this.fileContentService = fileContentService;
    }

    public void analyzeLogs(String fileName) throws Exception {
        log.info("Reading from file: " + fileName);
        List<LogEntry> logEntries = fileContentService.readFileContent(fileName);

        log.info("Processing file content of {} rows ", logEntries.size());
        List<EventInfoDTO> result = compactLog(logEntries);

        log.info("Content compacted to {} events", result.size());
        result.forEach(e -> log.debug(e.toString()));

    }

    private List<EventInfoDTO> compactLog(final List<LogEntry> logEntries) {
        EntryToEventConverter converter = new EntryToEventConverter();
        return converter.convertEntriesToEventsMap(logEntries).values().stream().collect(Collectors.toList());
    }
}
