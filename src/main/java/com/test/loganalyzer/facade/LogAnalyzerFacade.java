package com.test.loganalyzer.facade;

import com.test.loganalyzer.db.EventInfoDTO;
import com.test.loganalyzer.file.LogFileContentService;
import com.test.loganalyzer.file.LogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        compactLog(logEntries);

    }

    private Map<String, EventInfoDTO> compactLog(final List<LogEntry> logEntries) {
        //TODO
        return null;
    }
}
