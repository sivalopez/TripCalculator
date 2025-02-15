package com.sivalopez;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TripCalculatorTest {
    @Test
    void testGetTapGroupsByPan() {
        TripCalculator calculator = new TripCalculator();

        Tap tapOne = new Tap(1, parseDate("22-01-2023 13:00:00"), "ON", "Stop1", "Company1", "Bus37","5500005555555559");
        Tap tapTwo = new Tap(2, parseDate("22-01-2023 13:05:00"), "OFF", "Stop2", "Company1", "Bus37","5500005555555559");
        Tap tapThree = new Tap(3, parseDate("22-01-2023 09:20:00"), "ON", "Stop3", "Company1", "Bus36","4111111111111111");

        Map<String, List<Tap>> expectedTapGroups = new HashMap<>();
        expectedTapGroups.put("5500005555555559", Arrays.asList(tapOne, tapTwo));
        expectedTapGroups.put("4111111111111111", List.of(tapThree));

        Map<String, List<Tap>> tapGroups = calculator.getTapGroupsByPan(Arrays.asList(tapOne, tapTwo, tapThree));
        assertEquals(expectedTapGroups, tapGroups);
    }

    @Test
    void testFindAMatchingTapOffFromMultipleDates() {
        TripCalculator calculator = new TripCalculator();

        Tap tapOne = new Tap(1, parseDate("22-01-2023 13:00:00"), "ON", "Stop1", "Company1", "Bus37","5500005555555559");
        Tap tapTwo = new Tap(2, parseDate("22-01-2023 13:05:00"), "OFF", "Stop2", "Company1", "Bus37","5500005555555559");
        Tap tapThree = new Tap(3, parseDate("23-01-2023 09:20:00"), "ON", "Stop1", "Company1", "Bus37","5500005555555559");
        Tap tapFour = new Tap(4, parseDate("23-01-2023 09:25:00"), "OFF", "Stop2", "Company1", "Bus37","5500005555555559");

        Tap actualTap = calculator.findMatchingTapOff(tapOne, Arrays.asList(tapOne, tapTwo, tapThree, tapFour));
        assertEquals(tapTwo, actualTap);
    }

    @Test
    void testFindAnyMatchingTapOffForgotToTapOff() {
        TripCalculator calculator = new TripCalculator();

        Tap tapOne = new Tap(1, parseDate("22-01-2023 13:00:00"), "ON", "Stop1", "Company1", "Bus37","5500005555555559");
        Tap tapTwo = new Tap(2, parseDate("22-01-2023 19:20:00"), "ON", "Stop2", "Company1", "Bus37","5500005555555559");
        Tap tapThree = new Tap(3, parseDate("22-01-2023 19:30:00"), "OFF", "Stop1", "Company1", "Bus37","5500005555555559");

        Tap actualTap = calculator.findMatchingTapOff(tapOne, Arrays.asList(tapOne, tapTwo, tapThree));
        assertNull(actualTap);
    }

    private ZonedDateTime parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        return localDateTime.atZone(ZoneId.of("UTC"));
    }
}
