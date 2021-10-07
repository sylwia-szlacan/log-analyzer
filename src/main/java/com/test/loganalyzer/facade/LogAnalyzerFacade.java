package com.test.loganalyzer.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.test.loganalyzer.json.LogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Scanner;

@Service
public class LogAnalyzerFacade {

    private static Logger log = LoggerFactory.getLogger(LogAnalyzerFacade.class);
    private ObjectMapper objectMapper;

    LogAnalyzerFacade() {
        objectMapper = JsonMapper.builder().findAndAddModules().build();
    }

    public void analyzeLogs(String fileName) throws Exception {
        log.info("START reading from file: " + fileName);
        File file = new File(fileName);
        readFileContent(file);
        log.info("FINISH reading from file " + fileName);

    }

    private void readFileContent(File file) throws Exception {
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            convertToJson(line);
        }
        input.close();
    }

    private LogEntry convertToJson(String line) throws JsonProcessingException {
        LogEntry entry = objectMapper.readValue(line, LogEntry.class);
        log.debug("Log entry retrieved from file: " + entry.toString());
        return entry;
    }

}
