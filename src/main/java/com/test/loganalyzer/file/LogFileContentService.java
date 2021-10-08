package com.test.loganalyzer.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class LogFileContentService {
    private static Logger log = LoggerFactory.getLogger(LogFileContentService.class);

    private ObjectMapper objectMapper;

    LogFileContentService() {
        objectMapper = JsonMapper.builder()
                .findAndAddModules()
                .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
                .build();
    }

    public List<LogEntry> readFileContent(String fileName) throws Exception {
        File file = new File(fileName);
        List<LogEntry> entries = new ArrayList<>();
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            LogEntry entry = convertToJson(line);
            if (entry != null) {
                entries.add(entry);
            }
        }
        input.close();

        return entries;
    }

    LogEntry convertToJson(String line) throws JsonProcessingException {
        if(line == null || line.isEmpty()) {
            return null;
        }

        LogEntry entry = objectMapper.readValue(line, LogEntry.class);
        log.debug("Log entry retrieved from file: " + entry.toString());
        return entry;
    }

}
