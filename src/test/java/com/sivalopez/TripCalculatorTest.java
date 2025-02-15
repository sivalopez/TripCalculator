package com.sivalopez;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TripCalculatorTest {
    @Test
    void testSuccessfulTrip() {
        TripCalculator calculator = new TripCalculator();
        Tap tapOn = new Tap();
        Tap tapOff = new Tap();

        String startDateStr = "22-01-2023 13:00:00";
        String endDateStr = "22-01-2023 13:05:00";

        Trip expectedTrip = new Trip(parseDate(startDateStr), parseDate(endDateStr), 300);
        Trip actualTrip = calculator.calculateTrip(tapOn, tapOff);

        assertEquals(expectedTrip, actualTrip);
    }

    private ZonedDateTime parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        return localDateTime.atZone(ZoneId.of("UTC"));
    }
}
