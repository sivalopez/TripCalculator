package com.sivalopez;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TripCalculatorTest {
    Tap tapStart = new Tap(1, parseDate("22-01-2023 13:00:00"), "ON", "Stop1", "Company1", "Bus37","5500005555555559");
    Tap tapEnd = new Tap(2, parseDate("22-01-2023 13:05:00"), "OFF", "Stop2", "Company1", "Bus37","5500005555555559");

    @Test
    void testDetermineCompletedTripStatus() {
        TripCalculator calculator = new TripCalculator();
        String actualStatus = calculator.determineTripStatus(tapStart, tapEnd);

        assertEquals("COMPLETED", actualStatus);
    }

    @Test
    void testDetermineIncompleteTripStatus() {
        TripCalculator calculator = new TripCalculator();
        String actualStatus = calculator.determineTripStatus(tapStart, null);

        assertEquals("INCOMPLETE", actualStatus);
    }

    @Test
    void testDetermineCancelledTripStatus() {
        Tap tapEnd3mins = new Tap(2, parseDate("22-01-2023 13:03:00"), "OFF", "Stop1", "Company1", "Bus37","5500005555555559");

        TripCalculator calculator = new TripCalculator();
        String actualStatus = calculator.determineTripStatus(tapStart, tapEnd3mins);

        assertEquals("CANCELLED", actualStatus);
    }

    @Test
    void testGetTapGroupsByPan() {
        Tap tapThree = new Tap(3, parseDate("22-01-2023 09:20:00"), "ON", "Stop3", "Company1", "Bus36","4111111111111111");

        Map<String, List<Tap>> expectedTapGroups = new HashMap<>();
        expectedTapGroups.put("5500005555555559", Arrays.asList(tapStart, tapEnd));
        expectedTapGroups.put("4111111111111111", List.of(tapThree));

        TripCalculator calculator = new TripCalculator();
        Map<String, List<Tap>> tapGroups = calculator.getTapGroupsByPan(Arrays.asList(tapStart, tapEnd, tapThree));

        assertEquals(expectedTapGroups, tapGroups);
    }

    @Test
    void testFindAMatchingTapOffFromMultipleDates() {
        Tap tapThree = new Tap(3, parseDate("23-01-2023 09:20:00"), "ON", "Stop1", "Company1", "Bus37","5500005555555559");
        Tap tapFour = new Tap(4, parseDate("23-01-2023 09:25:00"), "OFF", "Stop2", "Company1", "Bus37","5500005555555559");

        TripCalculator calculator = new TripCalculator();
        Tap actualTap = calculator.findMatchingTapOff(tapStart, Arrays.asList(tapStart, tapEnd, tapThree, tapFour));

        assertEquals(tapEnd, actualTap);
    }

    @Test
    void testFindAnyMatchingTapOffForgotToTapOff() {
        Tap tapTwo = new Tap(2, parseDate("22-01-2023 19:20:00"), "ON", "Stop2", "Company1", "Bus37","5500005555555559");
        Tap tapThree = new Tap(3, parseDate("22-01-2023 19:30:00"), "OFF", "Stop1", "Company1", "Bus37","5500005555555559");

        TripCalculator calculator = new TripCalculator();
        Tap actualTap = calculator.findMatchingTapOff(tapStart, Arrays.asList(tapStart, tapTwo, tapThree));

        assertNull(actualTap);
    }

    private ZonedDateTime parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        return localDateTime.atZone(ZoneId.of("UTC"));
    }
}
