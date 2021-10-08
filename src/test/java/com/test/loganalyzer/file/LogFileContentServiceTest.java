package com.test.loganalyzer.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LogFileContentServiceTest {

    LogFileContentService service = new LogFileContentService();

    @Test
    void convertToJson() {
        //given
        String input = "{\"id\":\"blabla\", \"state\":\"Something\", \"timestamp\":1491377495216}";

        //when
        LogEntry logEntry = null;
        try {
            logEntry = service.convertToJson(input);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //then
        assertEquals("blabla", logEntry.getId());
    }

    @Test
    void convertToJsonThrowsException() {
        //given
        String input = "{\"id\":\"blabla\", \"some\":\"Something\", \"timestamp\":1491377495216}";

        //when + then
        assertThrows(JsonProcessingException.class, () -> service.convertToJson(input));
    }

    @Test
    void convertToJsonEmptyInput() {
        //given
        String emptyInput = "";
        String nullInput = null;

        //when + then
        try {
            LogEntry entryForEmptyInput = service.convertToJson(emptyInput);
            LogEntry entryForNullInput = service.convertToJson(nullInput);

            assertNull(entryForEmptyInput);
            assertNull(entryForNullInput);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}